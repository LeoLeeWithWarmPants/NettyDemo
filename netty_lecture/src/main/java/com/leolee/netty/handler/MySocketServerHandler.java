package com.leolee.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @ClassName MySocketServerHandler
 * @Description: websocket服务端自定义处理器
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MySocketServerHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        ctx.writeAndFlush(654321L);
    }

    /**
     * 功能描述: <br> 重写异常时的处理回调方法
     * 〈〉在这里遇到异常直接关闭掉链接
     * @Param: [ctx, cause]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/23 15:04
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
