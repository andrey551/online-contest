from pydantic import BaseModel

from runner.app.models.Base import TimeStampBase
from runner.app.schemas.enums.Status import Status


class Record(BaseModel):
    total: int
    passed: int
    status_list : list[Status]

