from pydantic import BaseModel


class RequestModel(BaseModel):
    status_code: int
    data: any
