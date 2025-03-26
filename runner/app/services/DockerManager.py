import logging
import os

import docker
from docker.models.containers import Container

from runner.app.exception.ImageDockerException import ImageDockerException
from runner.app.models.Resource import ContainerResource
from runner.app.models.Solution import SolutionRequest

logger = logging.getLogger(__name__)

class DockerManager:
    def __init__(self):
        self.client = docker.from_env()

    """ Check if image is existed, if not, pull it """
    def add_image(self, image: str):
        try:
            if image in self.client.images.list():
                logger.info("Image already exists")
            else:
                image = self.client.images.pull(image)
                logger.info( f"Pulling {image}")
                if image is None:
                    raise ImageDockerException("Can't pull image")
                logger.info( f"{image} has been pulled!")
        except Exception as e:
            logger.error(e)

    """ Remove image """
    def remove_image(self, image: str):
        try:
            logger.info(f"Removing {image}")
            self.client.images.remove(image)
            if self.client.images.get(image) is not None:
                raise ImageDockerException("Can't remove image, try again later!")
            logger.info( f"{image} has been removed!")
        except Exception as e:
            logger.error(e)

    """ Check if image is exist """
    def is_image_exists(self, image_name: str):
        if self.client.images.get(image_name) is not None:
            return False
        return True

    """ 
    Usage: Create container from specific image 
    Parameters:
        Image: Image name
        Command: command to run project in docker
            ex: For FastAPI project:  
                sh -c 'pip install -r requirements.txt && uvicorn app.main:app --host 0.0.0.0 --port 80'
        Volumes: Path to extracted project dir
            ex:  {os.path.abspath(project_dir): {'bind':'/app', 'mode': 'rw'}}
    """
    async def create_container(self, resource: ContainerResource, solution: SolutionRequest) -> Container|None:
        try:
            if not self.is_image_exists(resource["image_name"]):
                self.add_image(resource["image_name"])
            return self.client.containers.create(image = resource["image_name"],
                                                 detach=True,
                                                 command = resource["command"],
                                                 volumes={os.path.abspath(f'{solution.crt_dir}/{solution.extract_path}/{solution.file_name.rstrip('.zip')}'): {'bind':'/app', 'mode': 'rw'}},
                                                 working_dir="/app",
                                                 ports = {"80/tcp": solution.port},
                                                 user = resource["user"])
        except Exception as e:
            logger.error(e)
            return None

    """ Retrieve container by its name """
    def retrieve_container(self, container: str) -> Container|None:
        try:
            self.client.containers.get(container)
            return self.client.containers.get(container)
        except Exception as e:
            logger.error(e)

docker_container = DockerManager()



