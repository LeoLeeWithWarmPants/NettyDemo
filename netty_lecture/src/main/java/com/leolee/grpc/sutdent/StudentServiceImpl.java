package com.leolee.grpc.sutdent;


import com.leolee.protobuf.gRPC.Student.*;
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

    @Override
    public void getStudentByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("接收到客户端信息:" + request.getAge());

        //模拟返回都是某个相同年龄的同学
        responseObserver.onNext(StudentResponse.newBuilder()
                .setAge(request.getAge())
                .setName("LeoLee")
                .setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setAge(request.getAge())
                .setName("LeoLee2")
                .setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setAge(request.getAge())
                .setName("LeoLee3")
                .setCity("广东").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setAge(request.getAge())
                .setName("LeoLee4")
                .setCity("深圳").build());

        //返回结果，调用completed方法
        responseObserver.onCompleted();
    }

    /**
     * 功能描述: <br> 对于A client-side streaming RPC 来说，返回值很奇怪，以StudentRequest作为返回值，这里其实是通过一个回调来完成的返回
     * 〈〉换句话说：需要返回一个StreamObserver接口对象，我们来重写该接口的三个方法，当请求到服务端后，会回调这三个方法，所以在这三个方法内处理请求（不好理解啊！！！）
     * @Param: [responseObserver]
     * @Return: io.grpc.stub.StreamObserver<com.leolee.protobuf.gRPC.Student.StudentRequest>
     * @Author: LeoLee
     * @Date: 2020/9/13 16:08
     */
    @Override
    public StreamObserver<StudentRequest> getStudentWapperByAge(StreamObserver<StudentResponseList> responseObserver) {

        return new StreamObserver<StudentRequest>() {

            /**
             * 功能描述: <br> 当客户端流式请求发送的时候，每发送一个StudentRequest，该方法将会被回调一次
             * 〈〉
             * @Param: [value]
             * @Return: void
             * @Author: LeoLee
             * @Date: 2020/9/13 16:24
             */
            @Override
            public void onNext(StudentRequest value) {

                System.out.println("onNext request param [age]:" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {

                System.out.println("onError:" + t.getMessage());
            }

            /**
             * 功能描述: <br> 当客户端将流式请求一个一个发送给服务端完成后，回调该方法
             * 〈〉
             * @Param: []
             * @Return: void
             * @Author: LeoLee
             * @Date: 2020/9/13 16:28
             */
            @Override
            public void onCompleted() {

                //构造StudentResponseList返回对象
                StudentResponse studentResponse1 = StudentResponse.newBuilder()
                        .setName("LeoLee1")
                        .setAge(25)
                        .setCity("上海").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder()
                        .setName("LeoLee2")
                        .setAge(25)
                        .setCity("北京").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse1)
                        .addStudentResponse(studentResponse2)
                        .build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }
}
