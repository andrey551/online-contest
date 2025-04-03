from typing import Optional, Any

from pydantic import BaseModel, ConfigDict


class BaseResponse(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    status_code: Optional[int] = 200
    headers: Optional[dict] = None
    body: Any
