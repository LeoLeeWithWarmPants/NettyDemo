package com.leolee.netty.secondExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName MySocketServer
 * @Description: socket服务端
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MySocketServer {

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
                    .childHandler(new MySocketServerInitializer());//子处理器,自定义处理器

            //绑定监听端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //定义关闭监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //Netty提供的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
