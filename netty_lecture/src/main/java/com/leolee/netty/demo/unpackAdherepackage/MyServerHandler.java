package com.leolee.netty.demo.unpackAdherepackage;

import com.leolee.netty.demo.unpackAdherepackage.protocol.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @ClassName MyServerHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        byte[] content = msg.getContent();
        System.out.println("服务端接收到的数据:");
        System.out.println(new String(content, Charset.forName("utf-8")));
        System.out.println("服务端接收到数据量:" + ++this.count);

        //回复消息
        String responseMesage = UUID.randomUUID().toString();
        byte[] responseByte = responseMesage.getBytes(Charset.forName("utf-8"));
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLength(responseByte.length);
        messageProtocol.setContent(responseByte);
        ctx.writeAndFlush(messageProtocol);
    }
}
