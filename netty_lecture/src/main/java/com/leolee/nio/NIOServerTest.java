package com.leolee.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName NIOServerTest
 * @Description: 客户端服务端完整测试——模拟简单群聊,服务端单端口监听
 * @Author LeoLee
 * @Date 2020/9/23
 * @Version V1.0
 **/
public class NIOServerTest {

    //该map维护通道的唯一标识和通道对象本身
    private Map<String, SocketChannel> clientChannelMap = new HashMap<>();

    public void buildServer () throws IOException {

        //服务端初始化
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//非阻塞模式（异步模式）
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8899));

        //构建selector
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //开始阻塞代码，进行请求监听
        while (true) {
            try {
                //开始监听selector中“感兴趣事件”的通道
                int number = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    //对应客户端的Channel,服务端通过此channel和客户端联系
                    final SocketChannel clientSocketChannel;

                    //开始判断SelectionKey的事件类型
                    if (selectionKey.isAcceptable()) {//客户端请求服务端，建立连接请求
                        //获取当前事件发生的通道，ServerSocketChannel的作用就是帮助服务端和客户端建立连接
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        try {
                            //建立服务端和客户端连接的操作
                            clientSocketChannel = serverSocketChannel.accept();
                            clientSocketChannel.configureBlocking(false);//非阻塞模式（异步模式）
                            //将与客户端建立好连接的channel注册到selector，并对“读”操作感兴趣
                            clientSocketChannel.register(selector, SelectionKey.OP_READ);

                            //连接建立后，给每一个对应客户端的channel分配唯一标识
                            clientChannelMap.put(UUID.randomUUID().toString(), clientSocketChannel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (selectionKey.isReadable()) {//判断客户端是否发送消息给服务端（通道是否有数据可读）
                        clientSocketChannel = (SocketChannel) selectionKey.channel();

                        //开始读数据
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        try {
                            int i = clientSocketChannel.read(byteBuffer);

                            if (i > 0) {//开始回写数据到客户端
                                byteBuffer.flip();
                                //字节转字符
                                Charset charset = Charset.forName("UTF-8");//设置字符集
                                String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println("接收到客户端[" + clientSocketChannel + "]的消息:" + receiveMessage);

                                //开始向发送者之外的客户端推送消息
                                //获取发送客户端的唯一标识
                                String sendKey = null;
                                for (Map.Entry<String, SocketChannel> entry : clientChannelMap.entrySet()) {
                                    if (entry.getValue().equals(clientSocketChannel)) {
                                        sendKey = entry.getKey();
                                        break;
                                    }
                                }
                                //推送消息
                                for (Map.Entry<String, SocketChannel> entry : clientChannelMap.entrySet()) {
                                    SocketChannel socketChannel = entry.getValue();
                                    //为每一个客户端写入数据
                                    ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024);
                                    writeByteBuffer.put((sendKey + ":" + receiveMessage).getBytes());//将发送的数据写入buffer
                                    writeByteBuffer.flip();
                                    socketChannel.write(writeByteBuffer);
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //重点重点重点重点重点重点重点重点重点重点重点
                //处理完每一个selectionKey，就从selectionKeys集合将它清理掉，不然会报空指针异常
                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 功能描述: <br> 运行该方法，即可使用[nc localhost 8899]命令，开启多个客户端进行消息发送测试
     * 〈〉
     * @Param: [args]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/24 0:13
     */
    public static void main(String[] args) throws IOException {

        NIOServerTest scTest = new NIOServerTest();
        scTest.buildServer();
    }
}
