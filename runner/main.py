# from app.schemas.enums.Language import Language
import logging

import docker
from anyio import sleep

from app.services.DockerManager import DockerManager, DockerResource
from app.exception.ImageDockerException import ImageDockerException
logging.basicConfig(filename='runner.log')
if __name__ == "__main__":
    manager = DockerManager()
    resource = DockerResource('node:slim', "npm install", "npm start")
    container = manager.createContainer(resource)