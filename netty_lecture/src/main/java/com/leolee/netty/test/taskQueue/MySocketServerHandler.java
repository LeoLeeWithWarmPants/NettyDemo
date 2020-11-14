package com.leolee.netty.test.taskQueue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @ClassName MySocketServerHandler
 * @Description: websocket服务端自定义处理器
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MySocketServerHandler extends SimpleChannelInboundHandler<String> {


    ExecutorService executorService = Executors.newCachedThreadPool();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //模拟同步执行的逻辑代码执行较长的时间
        /*System.out.println(ctx.channel().remoteAddress() + ":" + msg + ", then do something consume long time");
        Thread.sleep(10 * 1000);
        //返回数据
        ctx.channel().writeAndFlush("server wirite to client");
        */


        //使用taskQueue异步执行
        /*System.out.println(ctx.channel().remoteAddress() + ":" + msg + ", then do something consume long time");
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                //返回数据
                ctx.channel().writeAndFlush("server wirite to client");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/


        //用户自定义定时任务
        /*ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10 * 1000);
                //返回数据
                ctx.channel().writeAndFlush("server wirite to client");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, TimeUnit.SECONDS);*/


        //异步线程池
        Future future = executorService.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                try {
                    Thread.sleep(10 * 1000);
                    //返回数据
                    ctx.channel().writeAndFlush("server wirite to client");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        System.out.println(future.get());

        System.out.println("server operation is done, going on......");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush("server read msg complete");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + " is online");
    }

    /**
     * 功能描述: <br> 重写异常时的处理回调方法
     * 〈〉在这里遇到异常直接关闭掉链接
     * @Param: [ctx, cause]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/23 15:04
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
