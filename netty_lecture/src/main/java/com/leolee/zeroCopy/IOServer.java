package com.leolee.zeroCopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName IOServer
 * @Description: IO服务端接收数据，不对接受数据做任何处理，只用于模拟客户端的数据接收
 * @Author LeoLee
 * @Date 2020/10/13
 * @Version V1.0
 **/
public class IOServer {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] bytes = new byte[4096];
            while (true) {
                int readCount = dataInputStream.read(bytes, 0, bytes.length);
                if (readCount == -1) {
                    break;
                }
            }
        }
    }
}
