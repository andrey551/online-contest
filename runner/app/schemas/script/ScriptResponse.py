from pydantic import BaseModel

from runner.app.schemas.enums.Status import Status


class ScriptResponse(BaseModel):
    runtime: int
    passed: int
    failed: int
    memoryUsed: int
    result: Status
