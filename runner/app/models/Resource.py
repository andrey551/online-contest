from typing import Optional, List

from pydantic import BaseModel, Field, ConfigDict, SkipValidation
from uuid import UUID

class ContainerResource(BaseModel):
    image_name: str
    command: str
    working_dir: str
    ports: dict[str, int]
    user: str

class Resource(BaseModel):
#    model_config = ConfigDict(arbitrary_types_allowed=True)  # Allow arbitrary types
    problem_id: str
    list_images: List[ContainerResource]
