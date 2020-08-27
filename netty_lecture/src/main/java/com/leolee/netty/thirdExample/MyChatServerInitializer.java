package com.leolee.netty.thirdExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @ClassName MyChatServerInitializer
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/8/24
 * @Version V1.0
 **/
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //解码器，根据一定的分隔符对消息进行解码
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        //由于涉及到服务端和客户端的字符串数据，需要绑定字符串的编解码
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyChatServerHandler());
    }


}
