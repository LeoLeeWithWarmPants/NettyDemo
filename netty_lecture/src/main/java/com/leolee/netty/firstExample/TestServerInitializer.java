package com.leolee.netty.firstExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @ClassName TestServerInitializer
 * @Description: 初始化管道并绑定处理器
 * @Author LeoLee
 * @Date 2020/8/22
 * @Version V1.0
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //声明管道
        ChannelPipeline pipeline = ch.pipeline();
        //在管道最后增加一个处理器：HttpServerCodec，对请求进行编解码用
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //绑定自定义的处理提
        pipeline.addLast("TestHttpServerHandler", new TestHttpServerHandler());
    }
}
