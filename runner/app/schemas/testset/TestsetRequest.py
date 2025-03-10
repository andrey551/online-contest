from pydantic import BaseModel


class TestsetRequest(BaseModel):
    name: str
    description: str