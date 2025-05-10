package com.tad.file.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: file.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FileTaskServiceGrpc {

  private FileTaskServiceGrpc() {}

  public static final String SERVICE_NAME = "FileTaskService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<File.FileTaskRequest,
      File.FileTaskResponse> getHandleFileTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HandleFileTask",
      requestType = File.FileTaskRequest.class,
      responseType = File.FileTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<File.FileTaskRequest,
      File.FileTaskResponse> getHandleFileTaskMethod() {
    io.grpc.MethodDescriptor<File.FileTaskRequest, File.FileTaskResponse> getHandleFileTaskMethod;
    if ((getHandleFileTaskMethod = FileTaskServiceGrpc.getHandleFileTaskMethod) == null) {
      synchronized (FileTaskServiceGrpc.class) {
        if ((getHandleFileTaskMethod = FileTaskServiceGrpc.getHandleFileTaskMethod) == null) {
          FileTaskServiceGrpc.getHandleFileTaskMethod = getHandleFileTaskMethod =
              io.grpc.MethodDescriptor.<File.FileTaskRequest, File.FileTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HandleFileTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  File.FileTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  File.FileTaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileTaskServiceMethodDescriptorSupplier("HandleFileTask"))
              .build();
        }
      }
    }
    return getHandleFileTaskMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileTaskServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceStub>() {
        @Override
        public FileTaskServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTaskServiceStub(channel, callOptions);
        }
      };
    return FileTaskServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileTaskServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceBlockingStub>() {
        @Override
        public FileTaskServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTaskServiceBlockingStub(channel, callOptions);
        }
      };
    return FileTaskServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileTaskServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTaskServiceFutureStub>() {
        @Override
        public FileTaskServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTaskServiceFutureStub(channel, callOptions);
        }
      };
    return FileTaskServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void handleFileTask(File.FileTaskRequest request,
                                io.grpc.stub.StreamObserver<File.FileTaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleFileTaskMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FileTaskService.
   */
  public static abstract class FileTaskServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return FileTaskServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FileTaskService.
   */
  public static final class FileTaskServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FileTaskServiceStub> {
    private FileTaskServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FileTaskServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTaskServiceStub(channel, callOptions);
    }

    /**
     */
    public void handleFileTask(File.FileTaskRequest request,
                               io.grpc.stub.StreamObserver<File.FileTaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleFileTaskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FileTaskService.
   */
  public static final class FileTaskServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FileTaskServiceBlockingStub> {
    private FileTaskServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FileTaskServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTaskServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public File.FileTaskResponse handleFileTask(File.FileTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleFileTaskMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FileTaskService.
   */
  public static final class FileTaskServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FileTaskServiceFutureStub> {
    private FileTaskServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FileTaskServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTaskServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<File.FileTaskResponse> handleFileTask(
        File.FileTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleFileTaskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_FILE_TASK = 0;

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
        case METHODID_HANDLE_FILE_TASK:
          serviceImpl.handleFileTask((File.FileTaskRequest) request,
              (io.grpc.stub.StreamObserver<File.FileTaskResponse>) responseObserver);
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
          getHandleFileTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              File.FileTaskRequest,
              File.FileTaskResponse>(
                service, METHODID_HANDLE_FILE_TASK)))
        .build();
  }

  private static abstract class FileTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileTaskServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return File.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileTaskService");
    }
  }

  private static final class FileTaskServiceFileDescriptorSupplier
      extends FileTaskServiceBaseDescriptorSupplier {
    FileTaskServiceFileDescriptorSupplier() {}
  }

  private static final class FileTaskServiceMethodDescriptorSupplier
      extends FileTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileTaskServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FileTaskServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileTaskServiceFileDescriptorSupplier())
              .addMethod(getHandleFileTaskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
