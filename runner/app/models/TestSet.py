from typing import Optional, List, Union, Dict

from bson import ObjectId
from fastapi import Form
from pydantic import BaseModel, ConfigDict
from uuid import UUID

class Request(BaseModel):
    url: Optional[str]
    method: Optional[str]
    header: Optional[Dict[str, str]] = None
    body: Optional[str] = None

class Response(BaseModel):
    status_code: Optional[int]
    header: Optional[Dict[str, str]] = None
    body: Optional[str] = None

class Testcase(BaseModel):
    request: Request
    response: Response

class TestSet(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    _id: ObjectId = None
    problem_id: UUID = Form(...)
    data: Optional[List[Testcase]] = Form(...)
    max_response_time: float = Form(...)
    max_memory_size: int = Form(...)
