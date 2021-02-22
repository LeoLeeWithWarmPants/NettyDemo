package com.leolee.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MySocketServerInitializer
 * @Description: 一旦客户端和服务端建立联系之后initChannel就会被调用
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MySocketServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //声明管道
        ChannelPipeline pipeline = ch.pipeline();
        //编解码器
        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyLongToBytebufEncoder());
        //自定义处理器
        pipeline.addLast("mySocketServerHandler", new MySocketServerHandler());

    }

}
