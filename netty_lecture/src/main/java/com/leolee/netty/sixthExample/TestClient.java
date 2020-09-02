package com.leolee.netty.sixthExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName TestClient
 * @Description: 基于protobuf的客户端
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestClient {

    public static void main(String[] args) throws InterruptedException {

        //客户端只需要一个线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //声明客户端启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅关闭
            eventLoopGroup.shutdownGracefully();
        }
    }
}
