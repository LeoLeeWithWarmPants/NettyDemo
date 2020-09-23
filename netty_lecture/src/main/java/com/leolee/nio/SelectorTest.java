package com.leolee.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName SelectorTest
 * @Description: NIO socket编程demo，用于理解Selector
 * @Author LeoLee
 * @Date 2020/9/22
 * @Version V1.0
 **/
public class SelectorTest {

    //端口数组，用于和多个客户端建立连接后分配端口
    int[] ports = null;

    //起始端口
    int tempPort = 5000;

    //构造器初始化 端口数组ports，并从起始端口tempPort开始分配[size]个端口号
    public SelectorTest (int size) {
        this.ports = new int[size];
        for (int i = 0; i < size; i++) {
            this.ports[i] = tempPort + i;
        }
    }


    public void selectorTest () throws IOException {

        Selector selector = Selector.open();

        //windows系统下是sun.nio.ch.WindowsSelectorProvider，如果是linux系统，则是KQueueSelectorProvider
        //由于Selector.open()的源码涉及 sun 包下的代码，是非开源代码，具体实现不得而知
//        System.out.println(SelectorProvider.provider().getClass());//sun.nio.ch.WindowsSelectorProvider
//        System.out.println(sun.nio.ch.DefaultSelectorProvider.create().getClass());//sun.nio.ch.WindowsSelectorProvider

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//非阻塞模式
            ServerSocket serverSocket = serverSocketChannel.socket();
            //绑定端口
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", ports[i]);
            serverSocket.bind(address);

            //注册selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("[step1]监听端口:" + ports[i]);
        }

        //阻塞代码，始终监听来自客户端的连接请求
        while (true) {
            //获取我们“感兴趣的时间”已经准备好的通道，上面代码感兴趣的是SelectionKey.OP_ACCEPT，这里获取的就是SelectionKey.OP_ACCEPT事情类型准备好的通道
            //number为该“感兴趣的事件“的通道数量
            int number = selector.select();
            System.out.println("number:" + number);
            if (number > 0) {
                //由于selector中会有多个通道同时准备好,所以这里selector.selectedKeys()返回的是一个set集合
                Set<SelectionKey> selectionKeys =  selector.selectedKeys();
                System.out.println("[step2]selectionKeys:" + selectionKeys);
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //由于我们”感兴趣“的是SelectionKey.OP_ACCEPT，所以如下判断
                    if (selectionKey.isAcceptable()) {
                        //selectionKey.channel()返回是ServerSocketChannel的爷爷类SelectableChannel，所以做强制类型转换
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);//非阻塞模式

                        //重点重点重点重点重点重点重点重点重点重点
                        //将接收到的channel同样也注册到Selector上,Selector<--->channel<--->buffer,三者是双向的
                        socketChannel.register(selector, SelectionKey.OP_READ);//这时候”感兴趣的事件“是读操作，因为要接收客户端的数据了
                        //重点重点重点重点重点重点重点重点重点重点
                        //当以上代码执行完毕后，已经建立了服务端与客户端的socket连接，这时候就要移除Set集合中的selectionKey，以免之后重复创建该selectionKey对应的通道
                        iterator.remove();

                        System.out.println("[step3]成功获取客户端的连接:" + socketChannel);
                    } else if (selectionKey.isReadable()) {//判断selectionKey可读状态
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        int byteRead = 0;
                        while (true) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                            byteBuffer.clear();
                            int read = socketChannel.read(byteBuffer);
                            //判断数据是否读完
                            if (read <= 0) {
                                socketChannel.register(selector, SelectionKey.OP_READ);
                                break;
                            }

                            //写回数据，这里为了简单：读取什么数据，就写回什么数据
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                            byteRead += read;
                        }
                        System.out.println("[step4]读取:" + byteRead + ",来自与:" + socketChannel);

                        //重点重点重点重点重点重点重点重点重点重点
                        //当以上代码执行完毕后，已经完成了对某一个已经“读准备好”通道的读写操作，这时候就要移除Set集合中的selectionKey，以免之后重复读写该selectionKey对应的通道
                        iterator.remove();
                    }
                }
            }
        }
    }


    /*
     * 功能描述: <br> 使用nc命令连接服务端：nc 127.0.0.1 5000
     * 〈〉
     * @Param: [args]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/23 12:59
     */
    public static void main(String[] args) throws IOException {

        SelectorTest selectorTest = new SelectorTest(5);
        selectorTest.selectorTest();
    }
}
