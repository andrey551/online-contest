from datetime import datetime
from typing import List

from pydantic import BaseModel, Field
from uuid import UUID

from sqlalchemy import func


class TestCase(BaseModel):
    input: str
    output: str

class TimeStampBase(BaseModel):
    __abstract__ = True
    created: datetime = Field(default = func.now())
    updated: datetime = Field(default = func.now())

class Problem(BaseModel):
    id: UUID
    problem_id: UUID
    problem_name: str
    testcases: TestCase

    class Config:
        json_encoders = {UUID: lambda v: str(v)}

def ResponseModel(data, message):
    return {
        "data": [data],
        "code": 200,
        "message": message,
    }

def ErrorResponseModel(error, code, message):
    return {"error": error, "code": code, "message": message}