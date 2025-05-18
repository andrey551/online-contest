import logging

import docker
import grpc
from app.exception.ImageDockerException import ImageDockerException
from app.generated import submission_pb2_grpc, submission_pb2
from app.models.Resource import ContainerResource
from app.models.Solution import SolutionRequest
from docker.models.containers import Container
from google.protobuf.json_format import MessageToDict

logger = logging.getLogger(__name__)


class DockerManager:
    def __init__(self):
        self.client = docker.from_env()

    """ Check if image is existed, if not, pull it """

    async def add_image(self, image: str):
        try:
            # Check if image exists locally (case-insensitive)
            existing_images = [img.tags[0].lower()
                               for img in self.client.images.list()
                               if img.tags]
            if any(image.lower() in img for img in existing_images):
                logger.info(f"Image {image} already exists")
                return True

            # Pull with progress tracking
            logger.info(f"Pulling image {image}...")
            pull_log = self.client.api.pull(image, stream=True, decode=True)

            # Monitor pull progress
            for log in pull_log:
                if 'status' in log:
                    logger.debug(f"Docker: {log['status']}")
                if 'error' in log:
                    raise ImageDockerException(f"Pull failed: {log['error']}")

            logger.info(f"Successfully pulled {image}")
            return True

        except docker.errors.APIError as api_err:
            logger.error(f"Docker API error: {api_err}")
            raise ImageDockerException(f"API error pulling {image}")
        except Exception as e:
            logger.error(f"Unexpected error: {str(e)}")
            raise ImageDockerException(f"Failed to add image: {str(e)}")

    """ Remove image """
    def remove_image(self, image: str):
        try:
            logger.info(f"Removing {image}")
            self.client.images.remove(image)
            if self.client.images.get(image) is not None:
                raise ImageDockerException("Can't remove image, try again later!")
            logger.info(f"{image} has been removed!")
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
                sh -c 'pip install -r requirements.txt && uvicorn app.main:app
                --host 0.0.0.0 --port 80'
        Volumes: Path to extracted project dir
            ex:  {os.path.abspath(project_dir): {'bind':'/app', 'mode': 'rw'}}
    """
    async def create_container(self,
                               resource: ContainerResource,
                               solution: SolutionRequest) -> Container | None:
        try:
            if not self.is_image_exists(resource["image_name"]):
                await self.add_image(resource["image_name"])
            return (self.client
                    .containers
                    .create(image=resource["image_name"],
                            detach=True,
                            command=resource["command"],
                            volumes={
                                'runner_shared_volume': {
                                    'bind': '/app',
                                    'mode': 'rw'
                                },
                            },
                            working_dir=f'/app/{solution.file_name.rstrip(".zip")}',
                            ports={"80/tcp": solution.port},
                            user=resource["user"],
                            network="online-contest-net"))
        except Exception as e:
            logger.error(e)
            return None

    """ Retrieve container by its name """
    def retrieve_container(self, container: str) -> Container | None:
        try:
            self.client.containers.get(container)
            return self.client.containers.get(container)
        except Exception as e:
            logger.error(e)


async def create_submission(user_id: str, laboratory_id: str):
    try:
        with grpc.insecure_channel('submission-submission-service-1:9093') as channel:
            stub = submission_pb2_grpc.SubmissionTaskServiceStub(channel)
            response = stub.HandleCreateSubmission(
                submission_pb2.CreateSubmissionRequest(
                    user_id=user_id,
                    laboratory_id=laboratory_id))
            logger.info(f"Submission response: {response}")
        return MessageToDict(response)
    except Exception as e:
        raise RuntimeError(e)


docker_container = DockerManager()
