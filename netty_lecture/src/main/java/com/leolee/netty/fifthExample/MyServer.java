package com.leolee.netty.fifthExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @ClassName MyServer
 * @Description: webSocket
 * @Author LeoLee
 * @Date 2020/8/30
 * @Version V1.0
 **/
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        //定义线程组 EventLoopGroup为死循环
        //boss线程组一直在接收客户端发起的请求，但是不对请求做处理，boss会将接收到的请i交给worker线程组来处理
        //实际可以用一个线程组来做客户端的请求接收和处理两件事，但是不推荐
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //启动类定义
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //子处理器,自定义处理器，服务端可以使用childHandler或者handler,handlerr对应接收线程组（bossGroup），childHandler对应处理线程组（workerGroup）
                    .handler(new LoggingHandler(LogLevel.INFO))//日志处理器
                    .childHandler(new WebSocketChannelInitializer());

            //绑定监听端口
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            //定义关闭监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //Netty提供的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
