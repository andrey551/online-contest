from datetime import datetime
from typing import Optional

from bson import ObjectId
from pydantic import BaseModel, Field


class SolutionRequest(BaseModel):
    problem_id: str
    author_id: str
    file_name: str
    crt_dir : Optional[str] = None
    zip_path: str = Field(default='/zip')
    extract_path: str = Field(default='/extract')
    port: int = Field(default=8080)
    submit_time: datetime = Field(default_factory=datetime.now)


class Solution(BaseModel):
    _id: ObjectId = None
    problem_id: str = None
    container_id: str = None
    author_id: str = None
    file_name: str = None
    submit_time: datetime = datetime.now()

def to_solution(solution_request: SolutionRequest):
    return Solution(problem_id=solution_request.problem_id,
                    author_id=solution_request.author_id,
                     file_name=solution_request.file_name)

def to_solution(solution_request: SolutionRequest, container_id: str):
    return Solution(problem_id=solution_request.problem_id,
                    author_id=solution_request.author_id,
                     file_name=solution_request.file_name,
                    container_id=container_id)
