package com.leolee.netty.sixthExample.multiProtocol;

import com.leolee.protobuf.DataInfo2;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @ClassName TestServerInitializer
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(DataInfo2.DataPackage.getDefaultInstance()));
        pipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast(new TestServerHandler());
    }
}
