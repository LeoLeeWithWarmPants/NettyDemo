package com.leolee.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NIOClientTest
 * @Description: 客户端服务端完整测试——模拟简单群聊,本类为客户端,通过Scanner作为数据输入
 * @Author LeoLee
 * @Date 2020/9/24
 * @Version V1.0
 **/
public class NIOClientTest {

    public void buildClient () {

        try {
            //发起对服务端连接的建立
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);//非阻塞模式，异步模式
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);//注意：客户端这里注册的是 OP_CONNECT
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

            while (true) {//同样是阻塞代码，始终接收服务端的消息数据
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    if (selectionKey.isConnectable()) {//判断是否是已经连接状态，是否已经和服务端建立好了连接
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {//判断连接是否在这个通道中“进行”
                            try {
                                client.finishConnect();//需要手动的去完成连接的建立[Finishes the process of connecting a socket channel.]
                                //发送消息通知服务端:连接已建立
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((LocalDateTime.now() + ":连接成功").getBytes());
                                writeBuffer.flip();
                                client.write(writeBuffer);

                                //使用异步来接收键盘的标准输入流
                                ExecutorService executorService = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
                                executorService.submit(() -> {
                                    while (true) {
                                        writeBuffer.clear();
                                        InputStreamReader input = new InputStreamReader(System.in);
                                        BufferedReader bufferedReader = new BufferedReader(input);

                                        String sendMessage = bufferedReader.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //重点重点重点重点重点重点重点重点重点重点
                            try {
                                //在建立好连接之后，注册通道为：对读操作“感兴趣”，即通道关注于读操作，以便之后接收服务端的消息
                                client.register(selector, SelectionKey.OP_READ);
                            } catch (ClosedChannelException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (selectionKey.isReadable()) {//判断通道是否可读
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        //开始读取
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        try {
                            int read = client.read(readBuffer);
                            if (read > 0) {
                                String receiveMessage = new String(readBuffer.array(), 0 , read);
                                System.out.println(receiveMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //重点重点重点重点重点重点重点重点重点重点
                //处理完每一个selectionKey，就从selectionKeys集合将它清理掉，不然会报空指针异常
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        NIOClientTest test = new NIOClientTest();
        test.buildClient();
    }
}
