package com.leolee.scalableIOInJava;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @ClassName Handler
 * @Description: 处理器
 * @Author LeoLee
 * @Date 2020/10/16
 * @Version V1.0
 **/
public class Handler implements Runnable {

    //----------------------------------------------------------------
    //--------------------------Handler setup-------------------------
    //----------------------------------------------------------------

    final SocketChannel socket;
    final SelectionKey sk;

    static int MAXIN, MAXOUT = 1024;


    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
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

    //----------------------------------------------------------------
    //--------------------------Request handling----------------------
    //----------------------------------------------------------------

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }

}

