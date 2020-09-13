package com.leolee.grpc.sutdent;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GrpcServer
 * @Description: gRPC服务端，可参考官网在github中的示例
 * @Author LeoLee
 * @Date 2020/9/12
 * @Version V1.0
 **/
public class GrpcServer {

    private Server server;

    private void start () throws IOException {

        int port = 8899;
        this.server = ServerBuilder
                .forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();

        System.out.println("server started!");

        //调用JVM钩子，在server端推出之前，释放掉rpc调用
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutting down gRPC server since JVM is shutting down");
            try {
                GrpcServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("server shut down");
        }));

        System.out.println("start method execution finished");
    }

    private void stop () throws InterruptedException {

        if (server != null) {
            server.shutdown()
                    .awaitTermination(30, TimeUnit.SECONDS);//等待30秒后退出
        }
    }

    /**
     * 功能描述: <br> 官方demo解释：Await termination on the main thread since the grpc library uses daemon threads.
     * 〈〉   实际就是为了给gRPC的server提供阻塞的能力，保持不退出
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/12 16:38
     */
    private void blockUntilShutdown() throws InterruptedException {

        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}
