package com.leolee.netty.demo.groupChat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName GroupChatClientHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
