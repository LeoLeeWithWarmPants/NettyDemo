package com.leolee.netty.fourthExample;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * @ClassName MyServerHandler
 * @Description: 继承ChannelInboundHandlerAdapter适配器
 * @Author LeoLee
 * @Date 2020/8/27
 * @Version V1.0
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 功能描述: <br> 当某一个事件触发后，userEventTriggered会调用ChannelHandlerContext这个上下文对象的fireUserEventTriggered(Object)，转发给pipeline中下一个ChannelInboundHandler(处理器)
     * 〈〉
     * @Param: [ctx, evt]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/27 17:28
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        //捕获空闲事件
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + ":" + eventType);
            //关闭空闲连接
            ctx.close();
        }
    }
}
