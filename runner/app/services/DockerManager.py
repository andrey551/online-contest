import logging

import docker
from docker.models.containers import Container

from runner.app.exception.ImageDockerException import ImageDockerException
from runner.app.models.Resource import ContainerResource


class DockerManager:
    def __init__(self):
        self.client = docker.from_env()

    """ Check if image is existed, if not, pull it """
    def add_image(self, image: str):
        try:
            if image in self.client.images.list():
                logging.info("Image already exists")
            else:
                image = self.client.images.pull(image)
                logging.info( f"Pulling {image}")
                if image is None:
                    raise ImageDockerException("Can't pull image")
                logging.info( f"{image} has been pulled!")
        except Exception as e:
            logging.error(e)

    """ Remove image """
    def remove_image(self, image: str):
        try:
            logging.info(f"Removing {image}")
            self.client.images.remove(image)
            if self.client.images.get(image) is not None:
                raise ImageDockerException("Can't remove image, try again later!")
            logging.info( f"{image} has been removed!")
        except Exception as e:
            logging.error(e)

    """ Check if image is exist """
    def is_image_exists(self, image_name: str):
        if self.client.images.get(image_name) is not None:
            return False
        return True

    """ Create container from specific image """
    def create_container(self, image: str, resource: ContainerResource) -> Container|None:
        try:
            if not self.is_image_exists(image):
                self.add_image(image)
            return self.client.containers.create(image = image,
                                                 detach=True,
                                                 volumes=resource.volumes,
                                                 working_dir=resource.working_dir,
                                                 ports = resource.ports,
                                                 user = resource.user,)
        except Exception as e:
            logging.error(e)
            return None

    """ Retrieve container by its name """
    def retrieve_container(self, container: str) -> Container|None:
        try:
            self.client.containers.get(container)
            return self.client.containers.get(container)
        except Exception as e:
            logging.error(e)





