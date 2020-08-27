package com.leolee.netty.thirdExample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName MyChatServerHandler
 * @Description:
 * @Author LeoLee
 * @Date 2020/8/25
 * @Version V1.0
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 保存每一个与客户端的连接通道
     * 拓展理解GlobalEventExecutor
     * Single-thread singleton {@link EventExecutor}.  It starts the thread automatically and stops it when there is no
     * task pending in the task queue for 1 second.  Please note it is not scalable to schedule large number of tasks to
     * this executor; use a dedicated executor.
     * 单线程、单例的，自动启动线程，如果任务队列在1秒中内没有挂起的任务的话，线程就将停止，可伸缩性不强
     */

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 功能描述: <br> 处理客户端发送的消息
     * 〈〉
     * @Param: [ctx, msg]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/25 13:35
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("服务端接收到[" + channel.remoteAddress() + "]的消息:" + msg);
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送消息:" + msg);
            } else {
                ch.writeAndFlush("我:" + msg);
            }
        });
    }


    /**
     * 功能描述: <br> 当客户端和服务端建立好连接后回调该方法
     * 〈〉可以把ChannelHandlerContext理解成connection的感觉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/25 12:47
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        //当有客户端建立和服务端的连接时候，需要对每个人进行广播上线消息，ChannelGroup提供了消息的写入和推送方法，不需要自己去遍历
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "加入\n");
        //当有一个客户端和服务端建立连接后告诉其他客户端，所以这个过程中，每建立一个与客户端的连接，服务端应该把这些channel对象（连接）保存起来，才在之后做到对每个客户端的上线广播
        channelGroup.add(channel);
    }


    /**
     * 功能描述: <br> 当与客户端断开连接后回调该方法
     * 〈〉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/25 13:06
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        //逻辑上是需要调用这行代码来移除离线客户端，但是在Netty中，会自动的移除该连接管道，这行代码写与不写没有影响
//        channelGroup.remove(channel);
        //广播断线消息
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "离开\n");
        System.out.println(channelGroup.size());
    }


    /**
     * 功能描述: <br> 客户端与服务端建立连接并处于活动状态
     * 〈〉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/25 13:13
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }


    /**
     * 功能描述: <br>
     * 〈〉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/25 13:16
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
