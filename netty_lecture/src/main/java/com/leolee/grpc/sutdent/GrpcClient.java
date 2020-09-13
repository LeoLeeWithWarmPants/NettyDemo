package com.leolee.grpc.sutdent;

import com.leolee.protobuf.gRPC.Student.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

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

    private StudentServiceGrpc.StudentServiceStub studentServiceStub;

    public GrpcClient (ManagedChannel channel) {

        //一种阻塞的Stub，必须等到请求响应返回的时候，才会往下继续执行
        studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(channel);

        //一种异步的stub，客户端是流式请求的时候使用
        studentServiceStub = StudentServiceGrpc.newStub(channel);
    }

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 8899)
                .usePlaintext()//Use of a plaintext connection to the server. By default a secure connection mechanism such as TLS will be used.
                .build();
        GrpcClient grpcClient = new GrpcClient(channel);

        System.out.println("———————————————————————A simple RPC———————————————————————");

        MyResponse response = grpcClient.studentServiceBlockingStub.getRealNameByUserName(MyRequest
                .newBuilder()
                .setUsername("LeoLee")
                .build());

        System.out.println("server response:" + response.getRealName());

        System.out.println("——————————————————————A server-side streaming RPC ————————————————————————");

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

        System.out.println("——————————————————————A client-side streaming RPC ————————————————————————");

        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {

            @Override
            public void onNext(StudentResponseList value) {

                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println("接收服务端响应");
                    System.out.println(studentResponse.getName());
                    System.out.println(studentResponse.getAge());
                    System.out.println(studentResponse.getCity());
                });
            }

            @Override
            public void onError(Throwable t) {

                System.out.println("onError:" + t.getMessage());
            }

            @Override
            public void onCompleted() {


            }
        };

        //发出对服务端的异步流请求
        StreamObserver<StudentRequest> studentRequestStreamObserver = grpcClient.studentServiceStub.getStudentWapperByAge(studentResponseListStreamObserver);
        //构造传递给服务端的参数
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                .setAge(25).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                .setAge(25).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                .setAge(25).build());
        studentRequestStreamObserver.onCompleted();

        //由于studentServiceStub = StudentServiceGrpc.newStub(channel);中studentServiceStub是异步的（详见StudentServiceGrpc.newStub），
        //导致请求还没有发送完成，当前线程就执行完毕了，所以如果想要看到相关的打印，需要让主线程多等待一会
        Thread.sleep(5000);
    }
}
