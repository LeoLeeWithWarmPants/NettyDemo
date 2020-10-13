package com.leolee.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOServer
 * @Description: NIO服务端接收数据，不对接受数据做任何处理，只用于模拟客户端的数据接收
 * @Author LeoLee
 * @Date 2020/10/13
 * @Version V1.0
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        //该选项用来决定如果网络上仍然有数据向旧的ServerSocket传输数据，是否允许新的ServerSocket绑定到与旧的ServerSocket同样的端口上，该选项的默认值与操作系统有关，在某些操作系统中，允许重用端口，而在某些系统中不允许重用端口。
        //
        //当ServerSocket关闭时，如果网络上还有发送到这个serversocket上的数据，这个ServerSocket不会立即释放本地端口，而是等待一段时间，确保接收到了网络上发送过来的延迟数据，然后再释放端口。
        //
        //值得注意的是，public void setReuseAddress(boolean on) throws SocketException必须在ServerSocket还没有绑定到一个本地端口之前使用，否则执行该方法无效。此外，两个公用同一个端口的进程必须都调用serverSocket.setReuseAddress(true)方法，才能使得一个进程关闭ServerSocket之后，另一个进程的ServerSocket还能够立刻重用相同的端口
        serverSocket.setReuseAddress(true);
        serverSocket.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            //阻塞模式
            socketChannel.configureBlocking(true);

            int readCount = 0;

            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }
    }
}
