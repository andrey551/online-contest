import os.path
import zipfile
import os

from pydantic import BaseModel

from runner.app.services.DockerManager import DockerManager, DockerResource

class RunnerResource(DockerResource):
    language: str

class Runner:
    def __init__(self):
        self.docker_manager = DockerManager()

    """ Unzip project """
    def extract_project(self, zip_path, extract_dir):
        with zipfile.ZipFile(zip_path) as zip_file:
            zip_file.extractall(extract_dir)

    """Run project in docker container"""
    def run_project_in_docker(self, resource : DockerResource):
        container = self.docker_manager.createContainer(resource)

        command = "sh -c '{build_command} && {run_command}'".format(build_command=resource.build_command , run_command=resource.run_command)

        """Create Docker container"""
        container.run(
            image=image,
            command = "sh -c '{build_command} && {run_command}'".format(build_command=build_command , run_command=run_command),
            volumes = {os.path.abspath(project_dir): {'bind':'/app', 'mode': 'rw'}},
            working_dir = '/app',
            detach = True,
            ports = {'8000/tcp' : 8080},# Reflect container port to host
            user="root",
        )
        return container








