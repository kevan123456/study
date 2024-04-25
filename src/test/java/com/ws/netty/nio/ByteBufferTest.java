/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.nio;

import com.ws.base.TestBase;
import com.ws.util.ByteBufferUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wangshun
 * @date 2024-04-25
 * @see
 * @since 1.0.0
 */
public class ByteBufferTest extends TestBase {

    @Test
    public void testAllocate() {
        //虚拟机堆内存，读写效率低，受到GC影响
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
        //直接内存，减少拷贝次数，读写效率高，不受GC影响。但是分配内存效率低，使用不当内存泄露
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(10);
        System.out.println(byteBuffer1.getClass());
        System.out.println(byteBuffer2.getClass());
    }


    /**
     * 从文件管道读
     */
    @Test
    public void testFileChannel() {
        try (FileChannel fileChannel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            int len;
            do {
                len = fileChannel.read(byteBuffer);
                //切换为读模式
                byteBuffer.flip();

                //判断是否还有数据
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    System.out.println((char) b);
                }
                //切换为写模式
                byteBuffer.clear();
            } while (len != -1);


        } catch (IOException e) {

        }
    }


    @Test
    public void testFlip() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // a
        byteBuffer.put((byte) 0x61);
        //b
        byteBuffer.put((byte) 0x62);
        //c
        byteBuffer.put((byte) 0x63);
        ByteBufferUtil.debugAll(byteBuffer);


        //System.out.println(byteBuffer.get());
        //必须切换读模式
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        ByteBufferUtil.debugAll(byteBuffer);
    }

    /**
     * 字符串与ByteBuffer互相转换
     */
    @Test
    public void testEnCode() {
        ByteBuffer byteBuffer1 = StandardCharsets.UTF_8.encode("你好");
        ByteBuffer byteBuffer2 = Charset.forName("utf-8").encode("哈哈");
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(10);
        byteBuffer3.put("你好".getBytes());

        ByteBufferUtil.debugAll(byteBuffer1);
        ByteBufferUtil.debugAll(byteBuffer2);
        ByteBufferUtil.debugAll(byteBuffer3);

        CharBuffer charBuffer1 = StandardCharsets.UTF_8.decode(byteBuffer1);
        System.out.println(charBuffer1.getClass());
        System.out.println(charBuffer1.toString());

        CharBuffer charBuffer2 = Charset.forName("utf-8").decode(byteBuffer2);
        System.out.println(charBuffer2.getClass());
        System.out.println(charBuffer2.toString());

        //必须切换读模式
        byteBuffer3.flip();
        CharBuffer charBuffer3 = Charset.forName("utf-8").decode(byteBuffer3);
        System.out.println(charBuffer3.getClass());
        System.out.println(charBuffer3.toString());
    }


    /**
     * 分散读，集中写
     */
    @Test
    public void testScattering() {
        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer1 = ByteBuffer.allocate(7);
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(3);
            ByteBuffer byteBuffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{byteBuffer1, byteBuffer2, byteBuffer3});

            byteBuffer1.flip();
            byteBuffer2.flip();
            byteBuffer3.flip();
            ByteBufferUtil.debugAll(byteBuffer1);
            ByteBufferUtil.debugAll(byteBuffer2);
            ByteBufferUtil.debugAll(byteBuffer3);

        } catch (IOException e) {

        }
    }


}
