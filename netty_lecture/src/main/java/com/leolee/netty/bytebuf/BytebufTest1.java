package com.leolee.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @ClassName BytebufTest1
 * @Description: TODO
 * @Author LeoLee
 * @Date 2021/2/11
 * @Version V1.0
 **/
public class BytebufTest1 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("嘻嘻hello world", Charset.forName("UTF-8"));

        if (byteBuf.hasArray()) {
            //如果为真，则证明该bytebuf对象式堆上的缓冲，因为堆上的缓冲实际上是用字节数组来存储的数据
            byte[] array = byteBuf.array();

            //类型
            System.out.println(new String(array, Charset.forName("UTF-8")));

            System.out.println(byteBuf);//UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 6, cap: 6)
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            System.out.println(byteBuf.readableBytes());

            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.println((char) byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0, 6, Charset.forName("UTF-8")));
        }
    }
}
