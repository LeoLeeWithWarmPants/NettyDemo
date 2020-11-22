package com.leolee.netty.demo.unpackAdherepackage;

import com.leolee.netty.demo.unpackAdherepackage.protocol.MessageDecoder;
import com.leolee.netty.demo.unpackAdherepackage.protocol.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MyClientInitializer
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageEncoder());//加入自定义编码器
        pipeline.addLast(new MessageDecoder());//加入自定义解码器
        pipeline.addLast(new MyClientHandler());
    }
}
