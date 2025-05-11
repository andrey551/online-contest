package com.tad.submission.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: submission.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SubmissionTaskServiceGrpc {

  private SubmissionTaskServiceGrpc() {}

  public static final String SERVICE_NAME = "SubmissionTaskService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Submission.SubmissionTaskRequest,
      Submission.SubmissionTaskResponse> getHandleSubmissionTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HandleSubmissionTask",
      requestType = Submission.SubmissionTaskRequest.class,
      responseType = Submission.SubmissionTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Submission.SubmissionTaskRequest,
      Submission.SubmissionTaskResponse> getHandleSubmissionTaskMethod() {
    io.grpc.MethodDescriptor<Submission.SubmissionTaskRequest, Submission.SubmissionTaskResponse> getHandleSubmissionTaskMethod;
    if ((getHandleSubmissionTaskMethod = SubmissionTaskServiceGrpc.getHandleSubmissionTaskMethod) == null) {
      synchronized (SubmissionTaskServiceGrpc.class) {
        if ((getHandleSubmissionTaskMethod = SubmissionTaskServiceGrpc.getHandleSubmissionTaskMethod) == null) {
          SubmissionTaskServiceGrpc.getHandleSubmissionTaskMethod = getHandleSubmissionTaskMethod =
              io.grpc.MethodDescriptor.<Submission.SubmissionTaskRequest, Submission.SubmissionTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HandleSubmissionTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Submission.SubmissionTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Submission.SubmissionTaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SubmissionTaskServiceMethodDescriptorSupplier("HandleSubmissionTask"))
              .build();
        }
      }
    }
    return getHandleSubmissionTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Submission.CreateSubmissionRequest,
      Submission.CreateSubmissionResponse> getHandleCreateSubmissionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HandleCreateSubmission",
      requestType = Submission.CreateSubmissionRequest.class,
      responseType = Submission.CreateSubmissionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Submission.CreateSubmissionRequest,
      Submission.CreateSubmissionResponse> getHandleCreateSubmissionMethod() {
    io.grpc.MethodDescriptor<Submission.CreateSubmissionRequest, Submission.CreateSubmissionResponse> getHandleCreateSubmissionMethod;
    if ((getHandleCreateSubmissionMethod = SubmissionTaskServiceGrpc.getHandleCreateSubmissionMethod) == null) {
      synchronized (SubmissionTaskServiceGrpc.class) {
        if ((getHandleCreateSubmissionMethod = SubmissionTaskServiceGrpc.getHandleCreateSubmissionMethod) == null) {
          SubmissionTaskServiceGrpc.getHandleCreateSubmissionMethod = getHandleCreateSubmissionMethod =
              io.grpc.MethodDescriptor.<Submission.CreateSubmissionRequest, Submission.CreateSubmissionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HandleCreateSubmission"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Submission.CreateSubmissionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Submission.CreateSubmissionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SubmissionTaskServiceMethodDescriptorSupplier("HandleCreateSubmission"))
              .build();
        }
      }
    }
    return getHandleCreateSubmissionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubmissionTaskServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceStub>() {
        @Override
        public SubmissionTaskServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubmissionTaskServiceStub(channel, callOptions);
        }
      };
    return SubmissionTaskServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubmissionTaskServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceBlockingStub>() {
        @Override
        public SubmissionTaskServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubmissionTaskServiceBlockingStub(channel, callOptions);
        }
      };
    return SubmissionTaskServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubmissionTaskServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubmissionTaskServiceFutureStub>() {
        @Override
        public SubmissionTaskServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubmissionTaskServiceFutureStub(channel, callOptions);
        }
      };
    return SubmissionTaskServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void handleSubmissionTask(Submission.SubmissionTaskRequest request,
                                      io.grpc.stub.StreamObserver<Submission.SubmissionTaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleSubmissionTaskMethod(), responseObserver);
    }

    /**
     */
    default void handleCreateSubmission(Submission.CreateSubmissionRequest request,
                                        io.grpc.stub.StreamObserver<Submission.CreateSubmissionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleCreateSubmissionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SubmissionTaskService.
   */
  public static abstract class SubmissionTaskServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return SubmissionTaskServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SubmissionTaskService.
   */
  public static final class SubmissionTaskServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SubmissionTaskServiceStub> {
    private SubmissionTaskServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubmissionTaskServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubmissionTaskServiceStub(channel, callOptions);
    }

    /**
     */
    public void handleSubmissionTask(Submission.SubmissionTaskRequest request,
                                     io.grpc.stub.StreamObserver<Submission.SubmissionTaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleSubmissionTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void handleCreateSubmission(Submission.CreateSubmissionRequest request,
                                       io.grpc.stub.StreamObserver<Submission.CreateSubmissionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleCreateSubmissionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SubmissionTaskService.
   */
  public static final class SubmissionTaskServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SubmissionTaskServiceBlockingStub> {
    private SubmissionTaskServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubmissionTaskServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubmissionTaskServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Submission.SubmissionTaskResponse handleSubmissionTask(Submission.SubmissionTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleSubmissionTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public Submission.CreateSubmissionResponse handleCreateSubmission(Submission.CreateSubmissionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleCreateSubmissionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SubmissionTaskService.
   */
  public static final class SubmissionTaskServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SubmissionTaskServiceFutureStub> {
    private SubmissionTaskServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubmissionTaskServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubmissionTaskServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Submission.SubmissionTaskResponse> handleSubmissionTask(
        Submission.SubmissionTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleSubmissionTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Submission.CreateSubmissionResponse> handleCreateSubmission(
        Submission.CreateSubmissionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleCreateSubmissionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_SUBMISSION_TASK = 0;
  private static final int METHODID_HANDLE_CREATE_SUBMISSION = 1;

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
        case METHODID_HANDLE_SUBMISSION_TASK:
          serviceImpl.handleSubmissionTask((Submission.SubmissionTaskRequest) request,
              (io.grpc.stub.StreamObserver<Submission.SubmissionTaskResponse>) responseObserver);
          break;
        case METHODID_HANDLE_CREATE_SUBMISSION:
          serviceImpl.handleCreateSubmission((Submission.CreateSubmissionRequest) request,
              (io.grpc.stub.StreamObserver<Submission.CreateSubmissionResponse>) responseObserver);
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
          getHandleSubmissionTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              Submission.SubmissionTaskRequest,
              Submission.SubmissionTaskResponse>(
                service, METHODID_HANDLE_SUBMISSION_TASK)))
        .addMethod(
          getHandleCreateSubmissionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              Submission.CreateSubmissionRequest,
              Submission.CreateSubmissionResponse>(
                service, METHODID_HANDLE_CREATE_SUBMISSION)))
        .build();
  }

  private static abstract class SubmissionTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SubmissionTaskServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Submission.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SubmissionTaskService");
    }
  }

  private static final class SubmissionTaskServiceFileDescriptorSupplier
      extends SubmissionTaskServiceBaseDescriptorSupplier {
    SubmissionTaskServiceFileDescriptorSupplier() {}
  }

  private static final class SubmissionTaskServiceMethodDescriptorSupplier
      extends SubmissionTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SubmissionTaskServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SubmissionTaskServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubmissionTaskServiceFileDescriptorSupplier())
              .addMethod(getHandleSubmissionTaskMethod())
              .addMethod(getHandleCreateSubmissionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
