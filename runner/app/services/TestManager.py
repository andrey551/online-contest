import asyncio
import time
from datetime import datetime

import docker
import requests
from docker.models.containers import Container

from runner.app.models.Record import Record
from runner.app.models.TestSet import TestSet
from runner.app.schemas.enums.Status import Status
from runner.app.schemas.responses.ResponseModel import BaseResponse
from runner.app.utils.Checker import check
from runner.app.services.DockerManager import DockerManager

class TestManager:
    def __init__(self, docker_client: docker.DockerClient, container_id: str):
        self.docker_client = docker_client
        self.container: Container = docker_client.containers.get(container_id)

    """" Execute test, record the result """
    def executeTests(self, test: BaseResponse, max_response_time) -> Status | None:
        start = datetime.now()
        response = requests.request(
            test.method,
            test.url,
            headers=test.headers).content(test.body)
        end = datetime.now()
        timer = ((end - start).microseconds / 1000)
        status = check(response, test[1])

        if timer > max_response_time:
            return Status.TIMED_OUT

        return status

    # Release report
    def getReport(self, tests: TestSet,  container_id: str) -> Record | None:
        report = Record(len(tests.data), 0, [])
        return_ = True
        task = asyncio.create_task(self.tracking_memory(
            container_id,
            20,
            40,
            tests.max_memory_size,
            return_))

        for test in tests.data:
            result = self.executeTests(test[1], tests.max_response_time)

            if not return_:
                report.status_list.append(Status.MEMORY_EXCEEDED)
            else :
                report.status_list.append(result)

            if result == Status.PASSED:
                report.passed += 1

        task.done()

        return report

    async def tracking_memory(self, container: str, interval, duration, memory_limit, return_ = True):
        memory_usage = 0.0
        try:
            container = DockerManager.retrieve_container(container)
            start_time = time.time()
            while time.time() - start_time < duration:
                memory_stats = container.stats(stream=False)['memory_mb'] / 1024 ** 2
                if "usage" in memory_stats:
                    memory_usage = memory_stats["usage"]

                if memory_usage > memory_limit:
                    return_ =  False

                await asyncio.sleep(interval)

        except docker.errors.NotFound as e:
            raise RuntimeError(e)