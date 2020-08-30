package com.leolee.netty.fifthExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @ClassName WebSocketChannelInitializer
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/8/30
 * @Version V1.0
 **/
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //Http处理
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());//之后再学习
        //Netty对于http请求是分块或者分段的方式，比如一个请求发送的数据长度是1000，被切成了10段，该处理器就按照8192最大长度，去聚合这些请求数据
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(8192));

        //websocket处理
        //负责websocket的连接，以及控制frames（close Ping Pong）的处理，文字和二进制数据传递给下一个处理器处理，websocket的数据基于各种frames
        pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
