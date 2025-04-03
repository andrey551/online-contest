from pydantic import BaseModel

from runner.app.schemas.enums.Status import Status


class Record(BaseModel):
    total: int
    passed: int
    status_list : list[Status]
    time_response : list[int]
    memory_used : list[int]

