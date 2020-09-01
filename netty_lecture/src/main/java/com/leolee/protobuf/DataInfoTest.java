package com.leolee.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @ClassName DataInfoTest
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class DataInfoTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        //构造对象
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("LeoLee")
                .setAge(25)
                .setAddress("上海")
                .build();

        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
