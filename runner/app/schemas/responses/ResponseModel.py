from pydantic import BaseModel

class BaseResponse(BaseModel):
    status_code: int
    headers: dict
    body: str
#
# class ResponseSuccess(BaseResponse):
#     data: dict
#
# class ResponseError(BaseResponse):
#     message: str