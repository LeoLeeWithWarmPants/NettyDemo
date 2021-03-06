package com.leolee.netty.thirdExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName MyChatClient
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/8/26
 * @Version V1.0
 **/
public class MyChatClient {


    public static void main(String[] args) throws InterruptedException, IOException {

        //客户端只需要一个线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //声明客户端启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            //获取键盘输入内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //持续的监听并发送数据到服务端
            for (;;) {
                channel.writeAndFlush(bufferedReader.readLine() + "\n");
            }
        } finally {
            //优雅关闭
            eventLoopGroup.shutdownGracefully();
        }
    }

}
