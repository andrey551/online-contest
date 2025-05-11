from requests import Response

from app.schemas.enums.Status import Status
from app.schemas.responses.ResponseModel import BaseResponse


def check(response: Response, answer: BaseResponse) -> Status | None:
    if response.status_code != answer["status_code"]:
        return Status.FAILED
    for header in response.headers:
        if header.lower() not in answer["header"]:
            return Status.FAILED
    if response.content != answer["body"]:
        return Status.FAILED

    return Status.PASSED
