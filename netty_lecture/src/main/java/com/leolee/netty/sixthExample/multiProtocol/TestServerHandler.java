package com.leolee.netty.sixthExample.multiProtocol;

import com.leolee.protobuf.DataInfo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName TestServerHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo2.DataPackage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo2.DataPackage msg) throws Exception {

        System.out.println("msg.getPackageType().getNumber()：" + msg.getPackageType().getNumber());

        //msg.PackageType.forNumber(packType)
        switch (msg.getPackageType().getNumber()) {
            case 0:
                System.out.println(msg.getSudent().getName());
                System.out.println(msg.getSudent().getAge());
                System.out.println(msg.getSudent().getAddress());
                break;
            case 1:
                System.out.println(msg.getDog().getDogName());
                System.out.println(msg.getDog().getDogAge());
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端出现异常已关闭");
        cause.printStackTrace();
        ctx.close();
    }
}
