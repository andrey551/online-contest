import json
import logging

import grpc
from google.protobuf.json_format import MessageToDict

from app.generated import container_pb2_grpc
from app.generated import submission_pb2
from app.generated import submission_pb2_grpc
from app.generated import container_pb2
from app.models.Record import Record
from app.schemas.enums.Status import CustomJSONEncoder


logger = logging.getLogger(__name__)


async def get_container_id_by_laboratory_id(laboratory_id: str):
    try:
        with grpc.insecure_channel("localhost:50051") as channel:
            stub = container_pb2_grpc.RunnerServiceStub(channel)
            response = stub.getContainerId(
                container_pb2.getContainerIdRequest(laboratoryId=laboratory_id))
            logger.info(f"Response: {response}")
        return MessageToDict(response)
    except Exception as e:
        raise RuntimeError(e)


async def get_container_id_by_solution_id(solution_id: str):
    try:
        with grpc.insecure_channel("fastapi_grpc_app:50051") as channel:
            stub = container_pb2_grpc.RunnerServiceStub(channel)
            response = stub.getContainerId(
                container_pb2.getContainerIdRequest(solutionId=solution_id))
            logger.info(f"Response: {response}")
        return MessageToDict(response)
    except Exception as e:
        raise RuntimeError(e)


async def send_result_to_submission_service(result: Record, submission_id: str):
    try:
        with grpc.insecure_channel("submission-submission-service-1:9093") as channel:
            stub = submission_pb2_grpc.SubmissionTaskServiceStub(channel)
            response = stub.HandleSubmissionTask(
                submission_pb2.SubmissionTaskRequest(task_type=str("UPDATE_RESULT"),
                                                     submission_id=submission_id,
                                                     data=json.dumps(
                                                         result.dict(),
                                                         cls=CustomJSONEncoder)))
            logger.info(f"Response: {response}")
        return MessageToDict(response)
    except Exception as e:
        raise RuntimeError(e)
