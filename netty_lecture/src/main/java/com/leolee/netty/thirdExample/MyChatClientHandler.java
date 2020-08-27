package com.leolee.netty.thirdExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyChatClientHandler
 * @Description:
 * @Author LeoLee
 * @Date 2020/8/26
 * @Version V1.0
 **/
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {


    /**
     * 功能描述: <br> 客户端read0只做消息接收后的打印
     * 〈〉
     * @Param: [ctx, msg]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/26 19:40
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
