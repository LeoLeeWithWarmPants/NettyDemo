package com.leolee.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedByteBufferTest
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/19
 * @Version V1.0
 **/
public class MappedByteBufferTest {

    public static void MappedByteBufferTest () throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("file1.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }

    public static void main(String[] args) throws IOException {

        MappedByteBufferTest.MappedByteBufferTest();
    }
}
