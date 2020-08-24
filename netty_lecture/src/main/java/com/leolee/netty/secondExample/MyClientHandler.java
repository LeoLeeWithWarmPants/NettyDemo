package com.leolee.netty.secondExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @ClassName MyClientHandler
 * @Description:
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(ctx.channel().remoteAddress());
        System.out.println("Client output:" + msg);
        ctx.writeAndFlush("from client:" + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 功能描述: <br> 该回调方法是连接处理活跃状态
     * 〈〉由于demo没有办法模拟请求的发送，所以重写这个方法来模拟客户端的消息发送
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/23 16:55
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("模拟客户端发送消息");
    }
}
