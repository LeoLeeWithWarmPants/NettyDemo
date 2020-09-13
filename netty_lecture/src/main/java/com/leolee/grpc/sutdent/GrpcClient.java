package com.leolee.grpc.sutdent;

import com.leolee.protobuf.gRPC.Student.MyRequest;
import com.leolee.protobuf.gRPC.Student.MyResponse;
import com.leolee.protobuf.gRPC.Student.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @ClassName GrpcClient
 * @Description: gRPC客户端
 * @Author LeoLee
 * @Date 2020/9/12
 * @Version V1.0
 **/
public class GrpcClient {

    private StudentServiceGrpc.StudentServiceBlockingStub studentServiceBlockingStub;

    public GrpcClient (ManagedChannel channel) {

        studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 8899)
                .usePlaintext()//Use of a plaintext connection to the server. By default a secure connection mechanism such as TLS will be used.
                .build();

        GrpcClient grpcClient = new GrpcClient(channel);
        MyResponse response = grpcClient.studentServiceBlockingStub.getRealNameByUserName(MyRequest
                .newBuilder()
                .setUsername("LeoLee")
                .build());

        System.out.println("server response:" + response.getRealName());
    }
}
