from typing import Optional, Any

from pydantic import BaseModel, ConfigDict

from runner.app.models.Solution import Solution


class BaseResponse(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    status_code: Optional[int] = 200
    headers: Optional[dict] = None
    body: Any

class SendSolutionResponse(BaseModel):
    solution: Solution| None
    submission_id: str|None
