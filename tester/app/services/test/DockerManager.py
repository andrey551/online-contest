import docker
from docker.models.containers import Container


def get_container(container_id) -> Container | None:
    try:
        container = docker.from_env().containers.get(container_id)

        return container
    except Exception as e:
        print(e)
