import os
import uuid

import docker

from runner.app.models.Problem import Problem
from runner.app.schemas.ScriptRequest import ScriptRequest
from runner.app.schemas.ScriptResponse import ScriptResponse

class DockerRunner:
    def __init__(self):
        self.client = docker.from_env()

    # Run script with docker container
    async def execute(self, data: ScriptRequest, tester: Problem) -> ScriptResponse:
        temp_dir = f"/tmp/{uuid.uuid4()}"
        os.makedirs(temp_dir, exist_ok=True)

        script_filename = {
            "python": "script.py",
            "node": "script.js",
            "bash": "script.sh",
            "ruby": "script.rb",
            "php": "script.php",
            "java": "Main.java",
            "go": "script.go",
            "rust": "script.rs",
            "perl": "script.pl",
        }.get(str(data.language), "script")

        script_path = os.path.join(temp_dir, script_filename)

#        Write script to a file
        with open(script_path, "w") as f:
            f.write(data.code)

        images = {
            "python": "python:3.9",
            "node": "node:14",
            "bash": "bash",
            "ruby": "ruby:3",
            "php": "php:8",
            "java": "openjdk:11",
            "go": "golang:1.19",
            "rust": "rust:1.65",
            "perl": "perl:5",
        }

        if data.language not in images:
            raise Exception(f"Unknown language: {data.language}")

        commands = {
            "python": f"python {script_filename}",
            "node": f"node {script_filename}",
            "bash": f"bash {script_filename}",
            "ruby": f"ruby {script_filename}",
            "php": f"php {script_filename}",
            "java": f"javac {script_filename} && java Main",
            "go": f"go run {script_filename}",
            "rust": f"rustc {script_filename} && ./script",
            "perl": f"perl {script_filename}",
        }

#         Run the script in a Docker container
        try:
            container = self.client.containers.run(
                image=images[data.language],
                command=commands[data.language],
                volumes={temp_dir: {"bind": "/tmp", "mode": "rw"}},
                working_dir="/tmp",
                remove=True,
                stdout=True,
                stderr=True,
            )
            output = container.decode('utf-8')
        except Exception as e:
            output = str(e)
        finally:
            # Clean up the temporary directory
            os.remove(script_filename)
            os.remove(script_path)
            os.rmdir(temp_dir)

        return output

# Initialize the DockerRunner
runner = DockerRunner()

