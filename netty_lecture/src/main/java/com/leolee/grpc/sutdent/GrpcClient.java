package com.leolee.grpc.sutdent;

import com.leolee.protobuf.gRPC.Student.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;


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

        //一种阻塞的Stub，必须等到请求响应返回的时候，才会往下继续执行
        studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 8899)
                .usePlaintext()//Use of a plaintext connection to the server. By default a secure connection mechanism such as TLS will be used.
                .build();
        GrpcClient grpcClient = new GrpcClient(channel);

        System.out.println("——————————————————————————————————————————————");

        MyResponse response = grpcClient.studentServiceBlockingStub.getRealNameByUserName(MyRequest
                .newBuilder()
                .setUsername("LeoLee")
                .build());

        System.out.println("server response:" + response.getRealName());

        System.out.println("——————————————————————————————————————————————");

        //流式的返回，会将每一个返回对象放入一个迭代器中
        Iterator<StudentResponse> studentResponseList = grpcClient.studentServiceBlockingStub
                .getStudentByAge(StudentRequest.newBuilder()
                                .setAge(25)
                                .build());
        while (studentResponseList.hasNext()) {
            StudentResponse studentResponse = studentResponseList.next();
            System.out.println("name:" + studentResponse.getName());
            System.out.println("age:" + studentResponse.getAge());
            System.out.println("city:" + studentResponse.getCity());
        }
    }
}
