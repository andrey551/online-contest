import logging

from app.generated import container_pb2_grpc
from app.generated.container_pb2_grpc import RunnerServiceServicer
from app.services.solution.SolutionManager import get_container_id_by_solution_id
from grpc import aio

import app.generated.container_pb2 as container_pb2

logger = logging.getLogger(__name__)


class GrpcService(RunnerServiceServicer):
    def __init__(self):
        pass

    async def getContainerId(self, request, context):
        container_id = await get_container_id_by_solution_id(request.solutionId)
        return container_pb2.getContainerIdResponse(containerId=container_id)


async def serve():
    try:
        server = aio.server()
        container_pb2_grpc.add_RunnerServiceServicer_to_server(GrpcService(), server)
        server.add_insecure_port("[::]:50051")
        await server.start()
        logger.info('gRPC server started on port 50051')
        await server.wait_for_termination()
    except Exception as e:
        logger.error(e)
