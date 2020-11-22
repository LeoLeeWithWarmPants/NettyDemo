package com.leolee.netty.demo.unpackAdherepackage;

import com.leolee.netty.demo.unpackAdherepackage.protocol.MessageProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @ClassName MyClientHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    //连续发送10条信息，测试服务端接受的消息的拆包粘包情况
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (int i = 0; i < 10; i++) {
            String message = "hello server " + i + "|";
            byte[] bytes = message.getBytes(Charset.forName("utf-8"));
            int length = message.getBytes(Charset.forName("utf-8")).length;

            MessageProtocol messageProtocal = new MessageProtocol();
            messageProtocal.setLength(length);
            messageProtocal.setContent(bytes);
            channel.writeAndFlush(messageProtocal);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        byte[] bytes = msg.getContent();
        System.out.println("客户端接收到的数据:");
        System.out.println(new String(bytes, Charset.forName("utf-8")));
        System.out.println("客户端接收到数据量:" + ++this.count);
    }
}
