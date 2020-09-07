package com.leolee.thrift.person;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @ClassName ThriftClient
 * @Description: Thrift 客户端
 * @Author LeoLee
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class ThriftClient {

    public static void main(String[] args) {

        //服务端transport用的是TFramedTransport，客户端也要用TFramedTransport
        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 8899), 60);//对应服务端的传输层方法[TFramed]
        TProtocol protocol = new TCompactProtocol(transport);//对应服务端的协议层方法[TCompact]
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            //打开连接
            transport.open();
            Person person = client.getPersonByName("LeoLee");
            System.out.println("打印getPersonByName请求结果:");
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("------------------------");

            Person person2 = new Person();
            person2.setName("LeoLee");
            person2.setAge(25);
            person2.setMarried(false);
            client.savePerson(person2);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            transport.close();
        }

    }
}
