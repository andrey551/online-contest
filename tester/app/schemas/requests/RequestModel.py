from pydantic import BaseModel, ConfigDict


# class RequestModel(BaseModel):
#     status_code: int
#     data: any

class RunTestRequestModel(BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    laboratory_id: str
    solution_id: str
    submission_id: str
