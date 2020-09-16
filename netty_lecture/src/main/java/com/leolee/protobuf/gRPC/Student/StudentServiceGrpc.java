package com.leolee.protobuf.gRPC.Student;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *定义service层
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: gRPCStudent.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.leolee.protobuf.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.MyRequest,
      com.leolee.protobuf.gRPC.Student.MyResponse> getGetRealNameByUserNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUserName",
      requestType = com.leolee.protobuf.gRPC.Student.MyRequest.class,
      responseType = com.leolee.protobuf.gRPC.Student.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.MyRequest,
      com.leolee.protobuf.gRPC.Student.MyResponse> getGetRealNameByUserNameMethod() {
    io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.MyRequest, com.leolee.protobuf.gRPC.Student.MyResponse> getGetRealNameByUserNameMethod;
    if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUserNameMethod = getGetRealNameByUserNameMethod =
              io.grpc.MethodDescriptor.<com.leolee.protobuf.gRPC.Student.MyRequest, com.leolee.protobuf.gRPC.Student.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRealNameByUserName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUserName"))
              .build();
        }
      }
    }
    return getGetRealNameByUserNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest,
      com.leolee.protobuf.gRPC.Student.StudentResponse> getGetStudentByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentByAge",
      requestType = com.leolee.protobuf.gRPC.Student.StudentRequest.class,
      responseType = com.leolee.protobuf.gRPC.Student.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest,
      com.leolee.protobuf.gRPC.Student.StudentResponse> getGetStudentByAgeMethod() {
    io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest, com.leolee.protobuf.gRPC.Student.StudentResponse> getGetStudentByAgeMethod;
    if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentByAgeMethod = getGetStudentByAgeMethod =
              io.grpc.MethodDescriptor.<com.leolee.protobuf.gRPC.Student.StudentRequest, com.leolee.protobuf.gRPC.Student.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentByAge"))
              .build();
        }
      }
    }
    return getGetStudentByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest,
      com.leolee.protobuf.gRPC.Student.StudentResponseList> getGetStudentWapperByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentWapperByAge",
      requestType = com.leolee.protobuf.gRPC.Student.StudentRequest.class,
      responseType = com.leolee.protobuf.gRPC.Student.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest,
      com.leolee.protobuf.gRPC.Student.StudentResponseList> getGetStudentWapperByAgeMethod() {
    io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StudentRequest, com.leolee.protobuf.gRPC.Student.StudentResponseList> getGetStudentWapperByAgeMethod;
    if ((getGetStudentWapperByAgeMethod = StudentServiceGrpc.getGetStudentWapperByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentWapperByAgeMethod = StudentServiceGrpc.getGetStudentWapperByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentWapperByAgeMethod = getGetStudentWapperByAgeMethod =
              io.grpc.MethodDescriptor.<com.leolee.protobuf.gRPC.Student.StudentRequest, com.leolee.protobuf.gRPC.Student.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentWapperByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StudentResponseList.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentWapperByAge"))
              .build();
        }
      }
    }
    return getGetStudentWapperByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StreamRequest,
      com.leolee.protobuf.gRPC.Student.StreamResponse> getBidirectionalStreamTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BidirectionalStreamTalk",
      requestType = com.leolee.protobuf.gRPC.Student.StreamRequest.class,
      responseType = com.leolee.protobuf.gRPC.Student.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StreamRequest,
      com.leolee.protobuf.gRPC.Student.StreamResponse> getBidirectionalStreamTalkMethod() {
    io.grpc.MethodDescriptor<com.leolee.protobuf.gRPC.Student.StreamRequest, com.leolee.protobuf.gRPC.Student.StreamResponse> getBidirectionalStreamTalkMethod;
    if ((getBidirectionalStreamTalkMethod = StudentServiceGrpc.getBidirectionalStreamTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBidirectionalStreamTalkMethod = StudentServiceGrpc.getBidirectionalStreamTalkMethod) == null) {
          StudentServiceGrpc.getBidirectionalStreamTalkMethod = getBidirectionalStreamTalkMethod =
              io.grpc.MethodDescriptor.<com.leolee.protobuf.gRPC.Student.StreamRequest, com.leolee.protobuf.gRPC.Student.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BidirectionalStreamTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.leolee.protobuf.gRPC.Student.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("BidirectionalStreamTalk"))
              .build();
        }
      }
    }
    return getBidirectionalStreamTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub>() {
        @java.lang.Override
        public StudentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceStub(channel, callOptions);
        }
      };
    return StudentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub>() {
        @java.lang.Override
        public StudentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceBlockingStub(channel, callOptions);
        }
      };
    return StudentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub>() {
        @java.lang.Override
        public StudentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceFutureStub(channel, callOptions);
        }
      };
    return StudentServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *定义service层
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *A simple RPC
     * </pre>
     */
    public void getRealNameByUserName(com.leolee.protobuf.gRPC.Student.MyRequest request,
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUserNameMethod(), responseObserver);
    }

    /**
     * <pre>
     *A server-side streaming RPC
     * </pre>
     */
    public void getStudentByAge(com.leolee.protobuf.gRPC.Student.StudentRequest request,
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     *A client-side streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentRequest> getStudentWapperByAge(
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentWapperByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     *A bidirectional streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StreamRequest> bidirectionalStreamTalk(
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBidirectionalStreamTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUserNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.leolee.protobuf.gRPC.Student.MyRequest,
                com.leolee.protobuf.gRPC.Student.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USER_NAME)))
          .addMethod(
            getGetStudentByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.leolee.protobuf.gRPC.Student.StudentRequest,
                com.leolee.protobuf.gRPC.Student.StudentResponse>(
                  this, METHODID_GET_STUDENT_BY_AGE)))
          .addMethod(
            getGetStudentWapperByAgeMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.leolee.protobuf.gRPC.Student.StudentRequest,
                com.leolee.protobuf.gRPC.Student.StudentResponseList>(
                  this, METHODID_GET_STUDENT_WAPPER_BY_AGE)))
          .addMethod(
            getBidirectionalStreamTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.leolee.protobuf.gRPC.Student.StreamRequest,
                com.leolee.protobuf.gRPC.Student.StreamResponse>(
                  this, METHODID_BIDIRECTIONAL_STREAM_TALK)))
          .build();
    }
  }

  /**
   * <pre>
   *定义service层
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractAsyncStub<StudentServiceStub> {
    private StudentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *A simple RPC
     * </pre>
     */
    public void getRealNameByUserName(com.leolee.protobuf.gRPC.Student.MyRequest request,
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *A server-side streaming RPC
     * </pre>
     */
    public void getStudentByAge(com.leolee.protobuf.gRPC.Student.StudentRequest request,
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *A client-side streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentRequest> getStudentWapperByAge(
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudentWapperByAgeMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *A bidirectional streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StreamRequest> bidirectionalStreamTalk(
        io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBidirectionalStreamTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *定义service层
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *A simple RPC
     * </pre>
     */
    public com.leolee.protobuf.gRPC.Student.MyResponse getRealNameByUserName(com.leolee.protobuf.gRPC.Student.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUserNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *A server-side streaming RPC
     * </pre>
     */
    public java.util.Iterator<com.leolee.protobuf.gRPC.Student.StudentResponse> getStudentByAge(
        com.leolee.protobuf.gRPC.Student.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *定义service层
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *A simple RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.leolee.protobuf.gRPC.Student.MyResponse> getRealNameByUserName(
        com.leolee.protobuf.gRPC.Student.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USER_NAME = 0;
  private static final int METHODID_GET_STUDENT_BY_AGE = 1;
  private static final int METHODID_GET_STUDENT_WAPPER_BY_AGE = 2;
  private static final int METHODID_BIDIRECTIONAL_STREAM_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USER_NAME:
          serviceImpl.getRealNameByUserName((com.leolee.protobuf.gRPC.Student.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENT_BY_AGE:
          serviceImpl.getStudentByAge((com.leolee.protobuf.gRPC.Student.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENT_WAPPER_BY_AGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentWapperByAge(
              (io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentResponseList>) responseObserver);
        case METHODID_BIDIRECTIONAL_STREAM_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bidirectionalStreamTalk(
              (io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.leolee.protobuf.gRPC.Student.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUserNameMethod())
              .addMethod(getGetStudentByAgeMethod())
              .addMethod(getGetStudentWapperByAgeMethod())
              .addMethod(getBidirectionalStreamTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
