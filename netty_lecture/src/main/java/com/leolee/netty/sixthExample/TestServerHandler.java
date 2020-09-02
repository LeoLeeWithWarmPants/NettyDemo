package com.leolee.netty.sixthExample;

import com.leolee.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName TestServerHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
