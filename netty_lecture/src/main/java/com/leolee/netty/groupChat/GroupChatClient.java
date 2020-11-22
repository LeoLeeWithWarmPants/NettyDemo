package com.leolee.netty.groupChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName GroupChatClient
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class GroupChatClient {

    private int port;

    private String host;

    public GroupChatClient(int port, String host) {
        this.port = port;
        this.host = host;
    }


    public void run() throws IOException, InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new GroupChatClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //持续的监听并发送数据到服务端
            for (;;) {
                channel.writeAndFlush(bufferedReader.readLine() + "\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        GroupChatClient groupChatClient = new GroupChatClient(8899, "127.0.0.1");
        groupChatClient.run();
    }
}
