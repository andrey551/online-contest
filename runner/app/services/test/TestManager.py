import asyncio
import logging
from datetime import datetime

import requests
from docker.models.containers import Container

from runner.app.models.Record import Record
from runner.app.models.TestSet import TestSet, Testcase, Request
from runner.app.schemas.enums.Status import Status
from runner.app.utils.Checker import check
from runner.app.services.docker.DockerManager import DockerManager, docker_container

logger = logging.getLogger(__name__)

class TestManager:
    def __init__(self, docker_manager: DockerManager, container_id: str):
        self.docker_client = docker_manager.client
        self.container: Container = docker_manager.retrieve_container(container_id)

    """" Execute test, record the result """
    async def execute_tests(self, test: Testcase, max_response_time) -> Status | None:
        start = datetime.now()
        response = await send_request(test["request"])
        end = datetime.now()
        timer = ((end - start).microseconds / 1000)
        status = check(response, test["response"])
        if timer > max_response_time:
            return Status.TIMED_OUT

        return status

    # Release report
    async def get_report(self, tests: TestSet,  container_id: str) -> Record | None:
        report = Record(total= len(tests["data"]), passed = 0, status_list = [])
        return_ = True
        task = asyncio.create_task(self.tracking_memory(
            container_id,
            20,
            tests["max_memory_size"],
            return_))

        for test in tests["data"]:
            result = await self.execute_tests(test, tests["max_response_time"])

            if not return_:
                report.status_list.append(Status.MEMORY_EXCEEDED)
            else :
                report.status_list.append(result)

            if result == Status.PASSED:
                report.passed += 1

        task.done()
        logger.info(report)

        return report

    async def tracking_memory(self, container: str, interval, memory_limit, return_ = True):
        memory_usage = 0.0
        try:
            container = docker_container.retrieve_container(container)
            while True:
                memory_stats = container.stats(stream=False)
                if "usage" in memory_stats:
                    memory_usage = memory_stats["usage"]

                if memory_usage > memory_limit:
                    return_ =  False
                await asyncio.sleep(interval)

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