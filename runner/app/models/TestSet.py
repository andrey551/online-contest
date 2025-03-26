from typing import Optional, List, Union, Dict

from bson import ObjectId
from fastapi import Form
from pydantic import BaseModel, ConfigDict
from uuid import UUID

class Request(BaseModel):
    url: Optional[str]
    method: Optional[str]
    header: Optional[Dict[str, str]] = None
    body: Optional[str]

class Response(BaseModel):
    status_code: Optional[int]
    header: Optional[Dict[str, str]] = None
    body: Optional[str]

class TestSet(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    _id: ObjectId = None
    problem_id: UUID = Form(...)
    data: Optional[List[Union[Request, Response]]] = Form(...)
    max_response_time: float = Form(...)
    max_memory_size: int = Form(...)
