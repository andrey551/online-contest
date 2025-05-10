package com.tad.file.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: container.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RunnerServiceGrpc {

  private RunnerServiceGrpc() {}

  public static final String SERVICE_NAME = "RunnerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Container.getContainerIdRequest,
      Container.getContainerIdResponse> getGetContainerIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getContainerId",
      requestType = Container.getContainerIdRequest.class,
      responseType = Container.getContainerIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Container.getContainerIdRequest,
      Container.getContainerIdResponse> getGetContainerIdMethod() {
    io.grpc.MethodDescriptor<Container.getContainerIdRequest, Container.getContainerIdResponse> getGetContainerIdMethod;
    if ((getGetContainerIdMethod = RunnerServiceGrpc.getGetContainerIdMethod) == null) {
      synchronized (RunnerServiceGrpc.class) {
        if ((getGetContainerIdMethod = RunnerServiceGrpc.getGetContainerIdMethod) == null) {
          RunnerServiceGrpc.getGetContainerIdMethod = getGetContainerIdMethod =
              io.grpc.MethodDescriptor.<Container.getContainerIdRequest, Container.getContainerIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getContainerId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Container.getContainerIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Container.getContainerIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RunnerServiceMethodDescriptorSupplier("getContainerId"))
              .build();
        }
      }
    }
    return getGetContainerIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RunnerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RunnerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RunnerServiceStub>() {
        @Override
        public RunnerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RunnerServiceStub(channel, callOptions);
        }
      };
    return RunnerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RunnerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RunnerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RunnerServiceBlockingStub>() {
        @Override
        public RunnerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RunnerServiceBlockingStub(channel, callOptions);
        }
      };
    return RunnerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RunnerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RunnerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RunnerServiceFutureStub>() {
        @Override
        public RunnerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RunnerServiceFutureStub(channel, callOptions);
        }
      };
    return RunnerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getContainerId(Container.getContainerIdRequest request,
                                io.grpc.stub.StreamObserver<Container.getContainerIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetContainerIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RunnerService.
   */
  public static abstract class RunnerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return RunnerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RunnerService.
   */
  public static final class RunnerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RunnerServiceStub> {
    private RunnerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RunnerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RunnerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getContainerId(Container.getContainerIdRequest request,
                               io.grpc.stub.StreamObserver<Container.getContainerIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetContainerIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RunnerService.
   */
  public static final class RunnerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RunnerServiceBlockingStub> {
    private RunnerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RunnerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RunnerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Container.getContainerIdResponse getContainerId(Container.getContainerIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetContainerIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RunnerService.
   */
  public static final class RunnerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RunnerServiceFutureStub> {
    private RunnerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RunnerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RunnerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Container.getContainerIdResponse> getContainerId(
        Container.getContainerIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetContainerIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CONTAINER_ID = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CONTAINER_ID:
          serviceImpl.getContainerId((Container.getContainerIdRequest) request,
              (io.grpc.stub.StreamObserver<Container.getContainerIdResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetContainerIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              Container.getContainerIdRequest,
              Container.getContainerIdResponse>(
                service, METHODID_GET_CONTAINER_ID)))
        .build();
  }

  private static abstract class RunnerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RunnerServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Container.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RunnerService");
    }
  }

  private static final class RunnerServiceFileDescriptorSupplier
      extends RunnerServiceBaseDescriptorSupplier {
    RunnerServiceFileDescriptorSupplier() {}
  }

  private static final class RunnerServiceMethodDescriptorSupplier
      extends RunnerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RunnerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RunnerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RunnerServiceFileDescriptorSupplier())
              .addMethod(getGetContainerIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
