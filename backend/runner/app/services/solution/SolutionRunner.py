import logging

from app.models.Record import Record
from app.services.docker.DockerManager import docker_container
from app.services.solution.SolutionManager import get_solution
from app.services.test.TestManager import TestManager
from app.services.test.TestService import retrieve_test_by_problem_id

logger = logging.getLogger(__name__)


async def run_and_check_solution(solution_id: str) -> Record | None:
    solution = await get_solution(solution_id)

    if not solution:
        return None
    test_manager = TestManager(docker_container, solution["container_id"])

    tests = await retrieve_test_by_problem_id(solution["problem_id"])

    if not tests:
        return None

    report = await test_manager.get_report(tests, solution["container_id"])

    return report
