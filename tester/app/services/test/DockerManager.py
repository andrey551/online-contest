import socket

import docker
from docker.models.containers import Container


def get_container(container_id) -> Container | None:
    try:
        container = docker.from_env().containers.get(container_id)

        return container
    except Exception as e:
        print(e)


def get_container_name(container_id) -> str | None:
    try:
        container = docker.from_env().containers.get(container_id)
        return container.name
    except Exception as e:
        print(e)


def get_free_port():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(('', 0))
        return s.getsockname()[1]
