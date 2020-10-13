package com.leolee.zeroCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOClient
 * @Description: NIO客户端读取文件并发送给服务器
 * @Author LeoLee
 * @Date 2020/10/13
 * @Version V1.0
 **/
public class NIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        //建立到服务器连接
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));
        //设置为阻塞模式:为了保证更准确的验证“零拷贝”的效率，所以设置为阻塞，一直读完所有的文件数据再传递到服务端
        socketChannel.configureBlocking(true);

        //源文件
        String file = "C:" + File.separator + "Users" + File.separator + "LeoLee" + File.separator + "Desktop" + File.separator + "sqldeveloper-4.1.5.21.78-x64.zip";

        FileChannel fileChannel = new FileInputStream(file).getChannel();

        long startTime = System.currentTimeMillis();

        //发送数据
        //mac电脑（linux系统）中，可以直接使用long transferCount = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
        //并不需要如下循环，原因windows对一次传输的数据大小有限制（8388608bytes），所以不能依次传输所有数据，需要循环来传递
        //参考与https://blog.csdn.net/forget_me_not1991/article/details/80722386
        long position = 0;
        long size = fileChannel.size();
        long total = 0;
        while (position < size) {
            long transferCount = fileChannel.transferTo(position, fileChannel.size(), socketChannel);//这一步体现零拷贝
            System.out.println("发送：" + transferCount);
            if (transferCount <= 0) {
                break;
            }
            total += transferCount;
            position += transferCount;
        }


        System.out.println("发送总字节数:" + total + ",耗时:" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
