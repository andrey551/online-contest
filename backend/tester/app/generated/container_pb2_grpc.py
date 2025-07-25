# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import app.generated.container_pb2 as container__pb2

GRPC_GENERATED_VERSION = '1.71.0'
GRPC_VERSION = grpc.__version__
_version_not_supported = False

try:
    from grpc._utilities import first_version_is_lower
    _version_not_supported = first_version_is_lower(GRPC_VERSION,
                                                    GRPC_GENERATED_VERSION)
except ImportError:
    _version_not_supported = True

if _version_not_supported:
    raise RuntimeError(
        f'The grpc package installed is at version {GRPC_VERSION},'
        + ' but the generated code in container_pb2_grpc.py depends on'
        + f' grpcio>={GRPC_GENERATED_VERSION}.'
        + f' Please upgrade your grpc module to grpcio>={GRPC_GENERATED_VERSION}'
        + f' or downgrade your generated code using grpcio-tools<={GRPC_VERSION}.'
    )


class RunnerServiceStub(object):
    """
    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.getContainerId = channel.unary_unary('/RunnerService/getContainerId',
                                                  request_serializer=container__pb2
                                                  .getContainerIdRequest
                                                  .SerializeToString,
                                                  response_deserializer=container__pb2
                                                  .getContainerIdResponse
                                                  .FromString,
                                                  _registered_method=True)


class RunnerServiceServicer(object):
    """
    """

    def getContainerId(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_RunnerServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'getContainerId': grpc.unary_unary_rpc_method_handler(
                    servicer.getContainerId,
                    request_deserializer=container__pb2.getContainerIdRequest
                                                       .FromString,
                    response_serializer=container__pb2.getContainerIdResponse
                                                      .SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'RunnerService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))
    server.add_registered_method_handlers('RunnerService', rpc_method_handlers)


# This class is part of an EXPERIMENTAL API.
class RunnerService(object):
    """
    """

    @staticmethod
    def getContainerId(request,
                       target,
                       options=(),
                       channel_credentials=None,
                       call_credentials=None,
                       insecure=False,
                       compression=None,
                       wait_for_ready=None,
                       timeout=None,
                       metadata=None):
        return grpc.experimental.unary_unary(
            request,
            target,
            '/RunnerService/getContainerId',
            container__pb2.getContainerIdRequest.SerializeToString,
            container__pb2.getContainerIdResponse.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)
