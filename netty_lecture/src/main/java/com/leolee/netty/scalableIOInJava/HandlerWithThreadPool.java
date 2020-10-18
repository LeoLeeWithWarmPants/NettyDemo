package com.leolee.netty.scalableIOInJava;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName HandlerWithThreadPool
 * @Description: 使用线程池来编写受控且可调节的处理器
 * @Author LeoLee
 * @Date 2020/10/17
 * @Version V1.0
 **/
public class HandlerWithThreadPool implements Runnable {

    // uses util.concurrent thread pool
//    static PooledExecutor pool = new PooledExecutor(...);
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    static final int PROCESSING = 3;
    // ...

    final SocketChannel socket;
    final SelectionKey sk;

    static int MAXIN, MAXOUT = 1024;


    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    HandlerWithThreadPool(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        //Optionally try first read now（可选择立刻开始首次读取数据）
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() {
        /* ... */
        return false;
    }

    boolean outputIsComplete() {
        /* ... */
        return false;
    }

    void process() {
        /* ... */
    }

    synchronized void read() {
        // ...
        try {
            socket.read(input);
            if (inputIsComplete()) {
                state = PROCESSING;
                cachedThreadPool.execute(new Processer());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void send2() {
        cachedThreadPool.execute(new WriteProcesser());
    }

    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }


    synchronized void processAndHandOff() {
        process();
        state = SENDING; // or rebind attachment
        sk.interestOps(SelectionKey.OP_WRITE);
    }

    class Processer implements Runnable {
        public void run() {
            processAndHandOff();
        }
    }

    class WriteProcesser implements Runnable {

        @Override
        public void run() {
            try {
                send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        if (state == READING) {
            read();
        } else if (state == SENDING) {
            send2();
        }
    }
}
