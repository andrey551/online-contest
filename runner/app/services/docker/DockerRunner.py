import logging

from app.models.Resource import ContainerResource
from app.models.Solution import SolutionRequest
from app.services.docker.DockerManager import DockerManager
from app.utils.FileManager import extract

logger = logging.getLogger(__name__)

class Runner:
    def __init__(self):
        self.docker_manager = DockerManager()

    """ Run project in docker container """
    async def run_project_in_docker(self, resource : ContainerResource, solution: SolutionRequest):

        extract(f'{solution.crt_dir}/{solution.zip_path}/{solution.file_name}',
                f'{solution.crt_dir}/{solution.extract_path}')

        container = await self.docker_manager.create_container(resource, solution) # Create docker container
        logger.info(f'Created container {container}')
        container.start()

        return container.id

runner = Runner()








