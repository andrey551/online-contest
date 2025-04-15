import logging

from runner.app.generated import container_pb2_grpc
from runner.app.generated.container_pb2_grpc import RunnerServiceServicer
from runner.app.services.solution.SolutionManager import get_container_id_by_laboratory_id

import runner.app.generated.container_pb2 as container_pb2
from grpc import aio

logger = logging.getLogger(__name__)

class GrpcService(RunnerServiceServicer):
    def __init__(self):
        pass

    async def getContainerId(self, request, context):
        container_id = await get_container_id_by_laboratory_id(request.laboratoryId)
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


