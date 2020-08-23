package com.leolee.netty.firstExample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @ClassName TestHttpServerHandler
 * @Description: 自定义请求处理器
 * @Author LeoLee
 * @Date 2020/8/22
 * @Version V1.0
 **/
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 功能描述: <br> 读取客户端请求并返回相应
     * @Param: [ctx, msg]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/8/22 16:03
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            System.out.println("这是一次" + request.method().name() + "请求");
            URI uri = new URI(request.uri());
            System.out.println(uri.getPath());
            if (uri.getPath().equals("/favicon.ico")) {
                System.out.println("favicon.ico请求");
                return;
            }

            //构造返回内容
            ByteBuf content = Unpooled.copiedBuffer("Hello word", CharsetUtil.UTF_8);
            //构造相应包体
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //构造响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //返回客户端
            ctx.writeAndFlush(response);

            //可以手动关闭与客户端建立的联系
            ctx.channel().close();
        }
    }


    //重写ChannelInboundHandlerAdapter的部分方法，以助于分析当某些事件触发时候的回调过程分析

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }
}
