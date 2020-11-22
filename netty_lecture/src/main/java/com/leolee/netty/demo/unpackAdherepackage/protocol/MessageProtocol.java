package com.leolee.netty.demo.unpackAdherepackage.protocol;

/**
 * @ClassName MessageProtocol
 * @Description: TODO
 * @Author LeoLee
 * @Date 2020/11/22
 * @Version V1.0
 **/
public class MessageProtocol {

    private int length;//读取的长度

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
