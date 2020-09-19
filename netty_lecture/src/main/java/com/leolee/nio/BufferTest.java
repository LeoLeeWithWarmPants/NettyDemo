package com.leolee.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @ClassName BufferTest
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/9/17
 * @Version V1.0
 **/
public class BufferTest {


    public static void copyFileWithNIO () throws IOException {

        FileInputStream fileInputStream = new FileInputStream("file1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("file2.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);


        while (true) {
            byteBuffer.clear();
            System.out.println("----------buffer clear:");
            System.out.println("buffer option:" + byteBuffer.position());
            System.out.println("buffer limit:" + byteBuffer.limit());
            System.out.println("buffer capacity:" + byteBuffer.capacity());

            int i = inputChannel.read(byteBuffer);
            System.out.println("----------buffer read:");
            System.out.println("buffer option:" + byteBuffer.position());
            System.out.println("buffer limit:" + byteBuffer.limit());
            System.out.println("buffer capacity:" + byteBuffer.capacity());

            if (i == -1) {
                break;
            }
            byteBuffer.flip();
            System.out.println("----------buffer flip:");
            System.out.println("buffer option:" + byteBuffer.position());
            System.out.println("buffer limit:" + byteBuffer.limit());
            System.out.println("buffer capacity:" + byteBuffer.capacity());

            outputChannel.write(byteBuffer);
            System.out.println("----------buffer write:");
            System.out.println("buffer option:" + byteBuffer.position());
            System.out.println("buffer limit:" + byteBuffer.limit());
            System.out.println("buffer capacity:" + byteBuffer.capacity());
        }

        inputChannel.close();
        outputChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    /*
     * 功能描述: <br> 分析 option limit capacity的变化
     * 〈〉
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/18 11:19
     */
    public static void testIntBuffer () {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("init buffer:");
        System.out.println("limit:" + intBuffer.limit());
        System.out.println("position:" + intBuffer.position());
        System.out.println("capacity:" + intBuffer.capacity());

        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);
            System.out.println("put data into buffer:");
            System.out.println("limit:" + intBuffer.limit());
            System.out.println("position:" + intBuffer.position());
            System.out.println("capacity:" + intBuffer.capacity());
        }

        intBuffer.flip();
        System.out.println("after flip():");
        System.out.println("limit:" + intBuffer.limit());
        System.out.println("position:" + intBuffer.position());
        System.out.println("capacity:" + intBuffer.capacity());

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
        System.out.println("after get():");
        System.out.println("limit:" + intBuffer.limit());
        System.out.println("position:" + intBuffer.position());
        System.out.println("capacity:" + intBuffer.capacity());
    }

    /*
     * 功能描述: <br> ByteBuffer提供的基本数据类型存放
     * 〈〉
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/18 11:34
     */
    public static void testByteBuffer () {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putShort((short) 3213);
        byteBuffer.putInt(1);
        byteBuffer.putLong(112343242312312L);
        byteBuffer.putChar('你');
        byteBuffer.putFloat(12.232f);
        byteBuffer.putDouble(34.131d);

        byteBuffer.flip();

        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getFloat());
        System.out.println(byteBuffer.getDouble());

    }

    /*
     * 功能描述: <br> 验证buffer slice，切片buffer
     * 〈slice产生的新buffer拥有独立的option limit capacity，新的buffer的元素改变会影响源buffer，同样源buffer的改变也会影响新的buffer〉
     *  原因就是sliceBuffer和源buffer共享底层的数组
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/18 13:09
     */
    public static void testBufferSlice () {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }

        System.out.println("---------------验证新buffer元素改变对源buffer的影响----------------");

        byteBuffer.position(4);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer1 = byteBuffer.slice();

        for (int i = 0; i < sliceBuffer1.capacity(); i++) {
            sliceBuffer1.put(i, (byte) (sliceBuffer1.get(i) * 2));
        }


        byteBuffer.clear();//等价于byteBuffer.position(0); byteBuffer.limit(10);

        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }

        System.out.println("---------------验证源buffer元素改变对新buffer的影响----------------");

        byteBuffer.position(4);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer2 = byteBuffer.slice();

        System.out.println("源buffer改变前:");
        while (sliceBuffer2.hasRemaining()) {
            System.out.println(sliceBuffer2.get());
        }

        byteBuffer.put(4, (byte) 7);
        byteBuffer.put(5, (byte) 8);

        sliceBuffer2 = byteBuffer.slice();


        System.out.println("源buffer改变后:");
        while (sliceBuffer2.hasRemaining()) {
            System.out.println(sliceBuffer2.get());
        }

    }

    /*
     * 功能描述: <br> 只读buffer
     * 〈〉一个普通的buffer可以调用asReadOnlyBuffer()方法返回一个只读buffer对象。此对象不可逆
     *  任何对只读buffer的内容操作，都会返回一个ReadOnlyBufferException
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/18 14:35
     */
    public static void onlyReadBuffer () {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        ByteBuffer readOnlyBuf = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuf.getClass());
        readOnlyBuf.put((byte) 1);

    }

    /*
     * 功能描述: <br> 测试直接缓冲buffer
     * 〈〉
     * @Param: []
     * @Return: void
     * @Author: LeoLee
     * @Date: 2020/9/19 19:51
     */
    public static void testDirectBuffer () throws IOException {

        FileInputStream fileInputStream = new FileInputStream("file1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("file2.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3);

        while (true) {
            byteBuffer.clear();

            int i = inputChannel.read(byteBuffer);

            if (i == -1) {
                break;
            }
            byteBuffer.flip();

            outputChannel.write(byteBuffer);

        }

        inputChannel.close();
        outputChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {

        BufferTest.onlyReadBuffer();
    }
}
