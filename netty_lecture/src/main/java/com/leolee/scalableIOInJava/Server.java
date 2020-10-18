package com.leolee.scalableIOInJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description: 传统IO服务设计
 * @Author LeoLee
 * @Date 2020/10/16
 * @Version V1.0
 **/
public class Server implements Runnable {

    private static final int PORT = 8899;

    private static final int MAX_INPUT = 1024;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (!Thread.interrupted()) {
                new Thread(new Handler(ss.accept())).start();
            }
            // or, single-threaded, or a thread pool
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable {

        final Socket socket;

        Handler(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[MAX_INPUT];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //实际业务处理并返回处理结果
        private byte[] process(byte[] cmd) {
            /* ... */
            return null;
        }
    }
}
