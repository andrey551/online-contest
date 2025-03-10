class DockerResource:
    def __init__(self, image_name: str, build_command: str, run_command: str):
        self.image_name = image_name


class ContainerResource:
    def __init__(self, command: str, volumes: dict, working_dir: str, ports: dict, user: str):
        self.command = command
        self.volumes = volumes
        self.working_dir = working_dir
        self.ports = ports
        self.user = user