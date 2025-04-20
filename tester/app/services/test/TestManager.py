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
from app.services.test.gRPCService import get_container_id_by_solution_id, send_result_to_submission_service
from app.utils.Checker import check

logger = logging.getLogger(__name__)

class TestManager:
    def __init__(self):
        pass

    """" Execute test, record the result """
    async def execute_tests(self, test: Testcase, max_response_time, report: Record) -> Status | None:
        start = datetime.now()
        response = await send_request(test["request"])
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
                total= len(tests["data"]),
                passed = 0,
                status_list = [],
                time_response=[],
                memory_used=[])

            container = get_container(container_id["containerId"])

            if container is None:
                raise Exception(f"No container with id {container} found")

            task = asyncio.create_task(self.tracking_memory(
                container,
                report,
                20,
                tests["max_memory_size"]))
            await asyncio.sleep(5)

            for test in tests["data"]:
                result = await self.execute_tests(test, tests["max_response_time"], report)
                report.status_list.append(result)

                if result == Status.PASSED:
                    report.passed += 1

            task.cancel()
            logger.info(report)

            await send_result_to_submission_service(report, request.submission_id)
            return report
        except Exception as e:
            logger.error(e)

    async def tracking_memory(self, container: Container, report: Record, interval, memory_limit):
        try:
            while True:
                logger.info(f"Tracking memory for container {container.name}")
                memory_stats = container.stats(stream=False)
                usage = memory_stats.get("memory_stats", {}).get("usage")
                if usage is not None:
                    report.memory_used.append(usage/1024**2)
                    logger.info(f"Memory usage: {usage}")
                else:
                    logger.info("usage stat not found in memory_stats")
                await asyncio.sleep(interval/1000)

        except Exception as e:
            raise RuntimeError(e)

# Send request to solution's endpoint
async def send_request(request: Request):
    if request["method"] == "GET":
        response =  requests.get(request["url"], headers=request["header"])
    elif request["method"] == "DELETE":
        response = requests.delete(request["url"], headers=request["header"])
    elif request["method"] == "POST":
        if 'body' in request:
            response = requests.post(request["url"], headers=request["header"], data=request["body"])
        else:
            response = requests.post(request["url"], headers=request["header"])
    elif request["method"] == "PUT":
        if 'body' in request:
            response = requests.put(request["url"], headers=request["header"], data=request["body"])
        else:
            response = requests.put(request["url"], headers=request["header"])
    else:
        response = None

    logger.info(f"Response: {response.content.decode()}")

    return response



