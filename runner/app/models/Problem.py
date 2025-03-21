from pydantic import BaseModel
from uuid import UUID


class TestCase(BaseModel):
    input: str
    output: str


class Problem(BaseModel):
    id: UUID
    problem_id: UUID
    problem_name: str
    testcases: TestCase