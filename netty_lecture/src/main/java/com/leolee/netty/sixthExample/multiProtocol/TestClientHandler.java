package com.leolee.netty.sixthExample.multiProtocol;

import com.leolee.protobuf.DataInfo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestClientHandler
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo2.DataPackage> {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo2.DataPackage msg) throws Exception {

    }

    /**
     * 功能描述: <br> 连接建立变为活跃状态后，马上向服务端写入Student message
     * 〈〉
     * @Param: [ctx]
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/2 21:11
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //一秒执行一次
        executor.scheduleAtFixedRate(() -> {
            //随机生成 0 或者 1
            int packType = new Random().nextInt(2);

            switch (DataInfo2.DataPackage.PackageType.forNumber(packType)) {
                case STUDENT:
                    System.out.println("发送student");
                    DataInfo2.DataPackage dataPackage = DataInfo2.DataPackage.newBuilder()
                            .setPackageType(DataInfo2.DataPackage.PackageType.STUDENT)
                            .setSudent(DataInfo2.Student.newBuilder()
                                    .setName("LeoLee").setAge(25).setAddress("上海").build()).build();
                    ctx.channel().writeAndFlush(dataPackage);
                    break;
                case DOG:
                    System.out.println("发送dog");
                    DataInfo2.DataPackage dataPackage2 = DataInfo2.DataPackage.newBuilder()
                            .setPackageType(DataInfo2.DataPackage.PackageType.DOG)
                            .setDog(DataInfo2.Dog.newBuilder()
                                    .setDogName("恶霸犬").setDogAge(3).build()).build();
                    ctx.channel().writeAndFlush(dataPackage2);
                    break;
            }


        }, 0, 1, TimeUnit.SECONDS);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端出现异常已关闭");
        cause.printStackTrace();
        ctx.close();
    }
}
