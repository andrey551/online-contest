from pydantic import BaseModel, ConfigDict
from uuid import UUID

class Request(BaseModel):
    url: str
    method: str
    header: dict[str, str]
    body: str

class Response(BaseModel):
    status_code: int
    header: dict[str, str]
    body: str

class TestSet(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    problem_id: UUID
    data: list[(Request, Response)]
    max_response_time: float
    max_memory_size: int
