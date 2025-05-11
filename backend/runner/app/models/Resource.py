from typing import Optional, List
from uuid import UUID

from bson import ObjectId
from pydantic import BaseModel, ConfigDict


class ContainerResource(BaseModel):
    image_name: str
    command: str
    user: str


class Resource(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)  # Allow arbitrary types
    _id: Optional[ObjectId] = None
    laboratory_id: UUID
    list_images: List[ContainerResource]
