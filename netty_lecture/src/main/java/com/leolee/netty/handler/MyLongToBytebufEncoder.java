package com.leolee.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyLongToBytebufEncoder
 * @Description: 编码器
 * @Author LeoLee
 * @Date 2021/2/21
 * @Version V1.0
 **/
public class MyLongToBytebufEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("encode invoked");
        System.out.println("msg:" + msg);
        out.writeLong(msg);
    }
}
