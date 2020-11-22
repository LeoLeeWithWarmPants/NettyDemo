package com.leolee.netty.groupChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName GroupChatServerHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组，管理所有客户端的channel集合
    //GlobalEventExecutor.INSTANCE是全局时间执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private List<Channel> channelList = new ArrayList<>();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //一旦服务端和客户端简历连接，就会立刻回调该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //在该客户端加入channelGroup之前发送加入通知
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + "\n[客户端]" + channel.remoteAddress() + "加入\n");
        boolean b = channelGroup.add(channel);
        System.out.println(b);
        System.out.println("来自" + channel.remoteAddress()+ "," + channel.id().asLongText() + "成功加入,当前群聊人数:" + channelGroup.size());

        /*channelList.forEach(ch -> {
            ch.writeAndFlush(simpleDateFormat.format(new Date()) + "\n[客户端]" + channel.remoteAddress() + "加入\n");
        });
        channelList.add(channel);
        System.out.println("channelList.size:" + channelList.size());*/
    }

    //表示channel处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) + "\n[客户端]" + ctx.channel().remoteAddress() + "上线\n");
    }

    //channel处于非活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) + "\n[客户端]" + ctx.channel().remoteAddress() + "离线\n");
    }

    //断开连接
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        /*channelGroup.remove(channel);//其实不需要手动一处，每当handlerRemoved执行的时候，这个channel也是随之被移除的
        //在该客户端加入channelGroup之前发送加入通知
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + "\n[客户端]" + channel.remoteAddress() + "断开连接\n");
        System.out.println("channelGroup size:" + channelGroup.size());*/

        channelList.remove(channel);
        channelList.forEach(ch -> {
            ch.writeAndFlush(simpleDateFormat.format(new Date()) + "\n[客户端]" + channel.remoteAddress() + "断开连接\n");
        });
        System.out.println(channel.remoteAddress()+ "断开连接,当前群聊人数:" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + "\n用户[" + channel.remoteAddress() + "]:" + msg + "\n");
            } else {
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + "\n[我]:" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
