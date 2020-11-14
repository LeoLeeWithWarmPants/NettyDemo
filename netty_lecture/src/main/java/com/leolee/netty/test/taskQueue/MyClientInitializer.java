package com.leolee.netty.test.taskQueue;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @ClassName MyClientInitializer
 * @Description:
 * @Author LeoLee
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //声明管道
        ChannelPipeline pipeline = ch.pipeline();
        //绑定自带的解码器，就是对二进制数据的解析工具，至于解码器构造方法的参数之后详细分析
        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        //编码器
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
        //由于涉及到服务端和客户端的字符串数据，需要绑定字符串的编解码
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        //自定义处理器
        pipeline.addLast("myClientHandler", new MyClientHandler());
    }
}
