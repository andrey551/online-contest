from pydantic import BaseModel, Field
from uuid import UUID

from runner.app.models.Base import TimeStampBase

class ContainerResource(BaseModel):
    image_name: str
    command: str
    working_dir: str
    ports: dict[str, str]
    user: str

class Resource(TimeStampBase):
    problem_id: UUID = Field(...)
    list_images: [ContainerResource] = Field(...)
