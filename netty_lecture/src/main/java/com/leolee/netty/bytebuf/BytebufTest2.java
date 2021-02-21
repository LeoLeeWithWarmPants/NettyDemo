package com.leolee.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * @ClassName BytebufTest2
 * @Description: Bytebuf提供的三种缓冲区
 * 1.heap buffer：使用byte array维护数据
 * 优点：由于数据存放在JVM堆中，可以创建创建和释放，并且提供了可以直接访问内部字节的方法
 * 缺点：每次读写数据的时候，都需要先将数据复制到直接缓冲区中再进行网络的传输
 *
 * 2.direct buffer：java NIO的直接缓冲，在JVM之外分配内存空间
 * 优点：堆外分配，不会占用JVM的内存空间，使用socket进行数据传递的时，性能非常的好，与heapbuf相反，不需要将数据进行复制。
 * 缺点：也由于是分配在操作系统的本地内存中，所以内存空间的分配和释放要比heapbuffer复杂且慢。并不支持通过字节数组的方式访问数据
 * netty通过提供内存池解决该问题：预先已经申请好的内存用来分配direct buffer
 *
 * 对于后端的业务数据进行编解码的时候推荐使用heap buffer
 * 对于I/O通信线程在读写缓冲区的时候，推荐使用 direct buffer
 *
 * 3.copmosite buffer：符合缓冲区，实则是一个列表，里面可以承载heap buffer或者是direct buffer
 * @Author LeoLee
 * @Date 2021/2/15
 * @Version V1.0
 **/
public class BytebufTest2 {

    public static void main(String[] args) {
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();
        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(20);

        byteBufs.addComponents(heapBuf, directBuf);

//        byteBufs.removeComponent(0);

        Iterator<ByteBuf> iterator = byteBufs.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
