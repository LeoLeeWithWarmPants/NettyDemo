package com.leolee.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToLongDecoder
 * @Description: 解码器
 * @Author LeoLee
 * @Date 2021/2/21
 * @Version V1.0
 **/
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("decode invoked");
        System.out.println("readable bytes num:" + in.readableBytes());

        if (in.readableBytes() >= 8) {//long类型是8歌字节
            out.add(in.readLong());
        }
    }
}
