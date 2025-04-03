from runner.app.protos import container_pb2_grpc
from runner.app.protos.container_pb2_grpc import RunnerServiceServicer
from runner.app.services.solution.SolutionManager import get_container_id_by_laboratory_id

import runner.app.protos.container_pb2 as container_pb2
import grpc
from concurrent import futures

class GrpcService(RunnerServiceServicer):
    def __init__(self):
        pass

    async def getContainerId(self, request, context):
        container_id = await get_container_id_by_laboratory_id(request.laboratoryId)

        return container_pb2.getContainerIdResponse(container_id, 200)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    container_pb2_grpc.add_RunnerServiceServicer_to_server(RunnerServiceServicer(), server)
    server.add_insecure_port("[::]:50051")
    server.start()
    server.wait_for_termination()
