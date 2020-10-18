package com.leolee.scalableIOInJava;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName Reactor
 * @Description: 响应器（也就是事件循环组）
 * @Author LeoLee
 * @Date 2020/10/16
 * @Version V1.0
 **/
public class Reactor implements Runnable {

    //----------------------------------------------------------------
    //------------------------------初始化-----------------------------
    //----------------------------------------------------------------

    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    //也可以用下面的方法代替上面构造器中的方法
    /* Alternatively, use explicit SPI provider:
    SelectorProvider p = SelectorProvider.provider();
    selector = p.openSelector();
    serverSocket = p.openServerSocketChannel();
    */

    //----------------------------------------------------------------
    //--------------------------Dispatch Loop()---------------------
    //----------------------------------------------------------------


    @Override
    public void run() {// 通常这是在一个新的线程中执行的
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    //开始根据每一个selectedKeys派发
                    dispatch((SelectionKey) (it.next()));
                }
                selected.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }

    //----------------------------------------------------------------
    //--------------------------Acceptor------------------------------
    //----------------------------------------------------------------

    /**
     * @ClassName Acceptor
     * @Description: 请求接收器
     * @Author LeoLee
     * @Date 2020/10/16
     * @Version V1.0
     **/
    public class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
