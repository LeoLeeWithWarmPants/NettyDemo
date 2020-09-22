package com.leolee.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @ClassName FileLockTest
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/19
 * @Version V1.0
 **/
public class FileLockTest {

    public static void fileLock () throws IOException, InterruptedException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("file1.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(2, 6, false);
        System.out.println("filelock status:" + fileLock.isValid());
        System.out.println("filelock type:" + fileLock.isShared());

        Thread.sleep(20000);

        fileLock.release();//释放锁
        randomAccessFile.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        FileLockTest.fileLock();
    }
}
