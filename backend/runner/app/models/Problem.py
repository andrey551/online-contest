from uuid import UUID

from pydantic import BaseModel


class TestCase(BaseModel):
    input: str
    output: str


class Problem(BaseModel):
    id: UUID
    problem_id: UUID
    problem_name: str
    testcases: TestCase
