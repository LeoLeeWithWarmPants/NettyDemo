package com.leolee.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @ClassName CharsetTest
 * @Description: NIO中对字符集的处理（编解码）
 * @Author LeoLee
 * @Date 2020/10/9
 * @Version V1.0
 **/
public class CharsetTest {

    public static void main(String[] args) throws IOException {

        String inputFile = "charset_Test_input.txt";
        String outputFile = "charset_Test_output.txt";

        //读取文件数据
        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "rw");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        long inputLength = new File(inputFile).length();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputLength);

        //设置编解码格式
        Charset charset = Charset.forName("UTF-8");
//        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        //解码
        CharBuffer charBuffer = charsetDecoder.decode(inputData);

        //do something

        //编码
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        //写入到文件中
        outputFileChannel.write(byteBuffer);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}
