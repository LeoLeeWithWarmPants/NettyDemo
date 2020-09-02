package com.leolee.netty.sixthExample;

import com.leolee.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName TestClientHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

    }

    /**
     * 功能描述: <br> 连接建立变为活跃状态后，马上向服务端写入Student message
     * 〈〉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/2 21:11
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        DataInfo.Student student = DataInfo.Student
                .newBuilder()
                .setName("LeoLee")
                .setAge(25)
                .setAddress("上海")
                .build();

        ctx.channel().writeAndFlush(student);
    }
}
