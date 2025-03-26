import logging

from runner.app.models.Resource import ContainerResource
from runner.app.models.Solution import SolutionRequest
from runner.app.services.DockerManager import DockerManager
from runner.app.services.FileManager import extract

logger = logging.getLogger(__name__)

class Runner:
    def __init__(self):
        self.docker_manager = DockerManager()

    """ Run project in docker container """
    async def run_project_in_docker(self, resource : ContainerResource, solution: SolutionRequest):
        logger.info(f'{solution.crt_dir}/{solution.zip_path}/{solution.file_name}')
        logger.info(f'{solution.crt_dir}/{solution.extract_path}')

        extract(f'{solution.crt_dir}/{solution.zip_path}/{solution.file_name}',
                f'{solution.crt_dir}/{solution.extract_path}')

        container = await self.docker_manager.create_container(resource, solution) # Create docker container
        logger.info(f'Created container {container}')
        container.start()

        return container

runner = Runner()








