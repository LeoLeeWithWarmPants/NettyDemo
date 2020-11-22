package com.leolee.netty.fourthExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyServerInitializer
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/8/27
 * @Version V1.0
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //IdleStateHandler是空闲时间处理器，在一定时间没有读或者写操作，就会被触发
        //long readerIdleTime:该客户端有多久没有读了，才会发送一个心跳检测包检测是否连接
        //long writerIdleTime:该客户端多久没有写操作了，才会发送一个心跳检测包检测是否连接
        //long allIdleTime:该客户端多久没有读和写了，才会发送一个心跳检测包检测是否连接
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}
