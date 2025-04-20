from pydantic import BaseModel

from app.schemas.enums.Status import Status


class Record(BaseModel):
    total: int
    passed: int
    status_list : list[Status]
    time_response : list[float]
    memory_used : list[int]

