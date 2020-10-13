package com.leolee.zeroCopy;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName IOClient
 * @Description: IO客户端读取文件并发送给服务器
 * @Author LeoLee
 * @Date 2020/10/13
 * @Version V1.0
 **/
public class IOClient {

    public static void main(String[] args) throws IOException {

        //建立到服务端的连接
        Socket socket = new Socket("127.0.0.1", 8899);

        //源文件
        String file = "C:" + File.separator + "Users" + File.separator + "LeoLee" + File.separator + "Desktop" + File.separator + "sqldeveloper-4.1.5.21.78-x64.zip";
        //读取目标数据
        InputStream inputStream = new FileInputStream(file);

        //发送数据
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] bytes = new byte[4096];
        long readcout;
        long total = 0;
        long startTime = System.currentTimeMillis();

        while ((readcout = inputStream.read(bytes)) >= 0) {
            total += readcout;
            dataOutputStream.write(bytes);
        }

        System.out.println("发送总字节数:" + total + ",耗时:" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        inputStream.close();
        socket.close();

    }
}
