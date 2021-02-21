package com.leolee.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @ClassName BytebufTest0
 * @Description: TODO
 * @Author LeoLee
 * @Date 2021/2/11
 * @Version V1.0
 **/
public class BytebufTest0 {

    public static void main(String[] args) {

        //创建一个容量为10的Bytebuf对象
        ByteBuf byteBuf = Unpooled.buffer(10);

        //设置bytebuf中字节的value
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }

        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.getByte(i));
        }
    }
}
