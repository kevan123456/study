/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.aio;

import com.ws.base.TestBase;
import com.ws.util.ByteBufferUtil;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * @author wangshun
 * @date 2024-04-25
 * @see
 * @since 1.0.0
 */
public class AsynchronousFileChannelTest extends TestBase {
    //protected Logger log = Logger.getInstance(getClass());
    /**
     *
     */
    @Test
    public void test() {
        AsynchronousFileChannel fileChannel = null;
        try {
            fileChannel = AsynchronousFileChannel.open(Paths.get("data.txt"), StandardOpenOption.READ);
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            System.out.println("begin read");
            /**
             * 参数1：byteBuffer
             * 参数2：读取的起始位置
             * 参数3：附件
             * 参数4：回调对象
             */
            fileChannel.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("completed read");
                    attachment.flip();
                    ByteBufferUtil.debugAll(attachment);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {

                }
            });
            System.out.println("end read");
            //因为AsynchronousFileChannel是守护线程方式运行的，所以一定要主线程运行
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileChannel)) {
                try {
                    fileChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
