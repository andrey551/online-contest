import asyncio
import logging
from datetime import datetime

import requests
from docker.models.containers import Container

from app.models.Record import Record
from app.models.TestSet import Testcase, Request
from app.schemas.enums.Status import Status
from app.schemas.requests.RequestModel import RunTestRequestModel
from app.services.test.DockerManager import get_container
from app.services.test.TestService import retrieve_test_by_problem_id
from app.services.test.gRPCService import get_container_id_by_solution_id
from app.services.test.gRPCService import send_result_to_submission_service
from app.utils.Checker import check

from app.services.test.DockerManager import get_container_name

logger = logging.getLogger(__name__)

# Queues to do container metric
request_queue = asyncio.Queue()
response_queue = asyncio.Queue()


class TestManager:
    def __init__(self):
        pass

    """" Execute test, record the result """
    async def execute_tests(self,
                            test: Testcase,
                            max_response_time,
                            host,
                            report: Record) -> Status | None:
        start = datetime.now()
        response = await send_request(test["request"], host)
        end = datetime.now()
        timer = ((end - start).microseconds / 1000)
        report.time_response.append(timer)
        status = check(response, test["response"])
        if timer > max_response_time:
            return Status.TIMED_OUT

        return status

    # Release report
    async def get_report(self, request: RunTestRequestModel) -> Record | None:
        try:
            container_id = await get_container_id_by_solution_id(request.solution_id)
            if container_id is None:
                raise Exception(f"No container with id {request.solution_id} found")

            tests = await retrieve_test_by_problem_id(request.laboratory_id)

            if tests is None:
                raise Exception(f"No test with id {request.laboratory_id} found")

            report = Record(
                total=len(tests["data"]),
                passed=0,
                status_list=[],
                time_response=[],
                memory_used=[])

            container = get_container(container_id["containerId"])
            container_name = get_container_name(container_id["containerId"])

            if container is None:
                raise Exception(f"No container with id {container} found")
            if not await is_ready(container_name, tests["health_check_url"]):
                report.passed = 0
                report.status_list.append(Status.TIMED_OUT)
            else:
                task = asyncio.create_task(self.tracking_memory(
                    container,
                    report,
                    20,
                    tests["max_memory_size"]))
                await asyncio.sleep(5)
                for idx, test in enumerate(tests["data"], start=1):
                    await request_queue.put("START")
                    try:
                        result = await self.execute_tests(test,
                                                          tests["max_response_time"],
                                                          container_name, report)
                        report.status_list.append(result)
                        if result == Status.PASSED:
                            report.passed += 1
                    except Exception as e:
                        logger.error(f"Test {idx} failed with error: {e}")
                        report.status_list.append(Status.FAILED)
                    await request_queue.put("STOP")
                    await asyncio.sleep(0.1)

                task.cancel()
                logger.info(report)

            await send_result_to_submission_service(report, request.submission_id)
            container.stop()
            return report
        except Exception as e:
            logger.error(e)

    async def tracking_memory(self,
                              container: Container,
                              report: Record,
                              interval,
                              memory_limit):
        current_max_mem = 0.0
        active = False  # Whether we're tracking a test

        try:
            while True:
                # 1. Check for START/STOP commands (non-blocking)
                try:
                    cmd = request_queue.get_nowait()
                    if cmd == "START":
                        current_max_mem = 0.0
                        active = True
                    elif cmd == "STOP":
                        report.memory_used.append(current_max_mem)
                        active = False
                except asyncio.QueueEmpty:
                    pass

                # 2. Track memory only during active tests
                if active:
                    try:
                        stats = container.stats(stream=False)
                        mem_bytes = stats.get("memory_stats", {}).get("usage", 0)
                        mem_mb = mem_bytes / (1024 ** 2)
                        if mem_mb > current_max_mem:
                            current_max_mem = mem_mb
                    except Exception as e:
                        logger.warning(f"Memory stat error: {e}")

                # 3. Small delay to prevent CPU overload
                await asyncio.sleep(interval / 1000)

        except asyncio.CancelledError:
            logger.info("Memory tracking stopped")


# Send request to solution's endpoint
async def send_request(request: Request, host: str):
    url = f"http://{host}:80{request["url"]}"
    if request["method"] == "GET":
        response = requests.get(url, headers=request["header"])
    elif request["method"] == "DELETE":
        response = requests.delete(url, headers=request["header"])
    elif request["method"] == "POST":
        if 'body' in request:
            response = requests.post(url,
                                     headers=request["header"],
                                     data=request["body"])
        else:
            response = requests.post(url, headers=request["header"])
    elif request["method"] == "PUT":
        if 'body' in request:
            response = requests.put(url,
                                    headers=request["header"],
                                    data=request["body"])
        else:
            response = requests.put(url, headers=request["header"])
    else:
        response = None

    # logger.info(f"Response: {response.content.decode()}")

    return response


async def is_ready(host: str, health: str) -> bool:
    url = f"http://{host}:80{health}"
    start_time = datetime.now()

    while True:
        try:
            response = requests.get(url, timeout=2)
            if response.status_code == 200:
                return True
        except requests.exceptions.RequestException:
            pass  # Ignore failures and retry

        if (datetime.now() - start_time).total_seconds() > 120:
            return False

        await asyncio.sleep(2)
