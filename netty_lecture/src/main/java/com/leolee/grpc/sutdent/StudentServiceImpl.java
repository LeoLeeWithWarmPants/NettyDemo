package com.leolee.grpc.sutdent;


import com.leolee.protobuf.gRPC.Student.MyRequest;
import com.leolee.protobuf.gRPC.Student.MyResponse;
import com.leolee.protobuf.gRPC.Student.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @ClassName StudentServiceImpl
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/12
 * @Version V1.0
 **/
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {

        System.out.println("接收到客户端信息:" + request.getUsername());

        //组织返回结果，需要注意的是gRPC的service 是通过StreamObserver参数来返回结果的，所以方法是 void
        responseObserver.onNext(MyResponse.newBuilder()
                .setRealName("LYL")
                .build()
        );

        //返回结果，调用completed方法
        responseObserver.onCompleted();
    }
}
