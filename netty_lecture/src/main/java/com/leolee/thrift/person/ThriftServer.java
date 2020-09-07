package com.leolee.thrift.person;

import com.leolee.thrift.person.serviceImpl.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @ClassName ThriftServer
 * @Description: thrift服务端
 * @Author LeoLee
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8899);//socket通道对象创建
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).minWorkerThreads(2).maxWorkerThreads(4);
        //绑定Service层处理器
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        //工厂
        arg.protocolFactory(new TCompactProtocol.Factory());//协议层工厂对象，该对象会将二进制字节码，最大限度的压缩
        arg.transportFactory(new TFramedTransport.Factory());//构建Framed传输层工厂对象
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift server started");

        //死循环，源源不断的接受socket中传递的请求
        server.serve();
    }
}
