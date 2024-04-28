/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import com.ws.base.TestBase;
import com.ws.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
public class ByteBufTest extends TestBase {
    
    /**
     * 自动扩容
     * 数据小于512，下一个选择16的整数倍
     * 数据大于512，下一个选择2的n次方
     */
    @Test
    public void testWriteBytes() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        ByteBufUtil.log(byteBuf);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 300; i++) {
            sb.append(i);
        }
        byteBuf.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));
        ByteBufUtil.log(byteBuf);
    }

    /**
     * 池化和非池化
     * 可以启动参数设置-Dio.netty.allocator.type=unpooled|pooled}
     */
    @Test
    public void testPooled() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        //Pooled表示池化
        System.out.println(byteBuf.getClass());
    }

    /**
     * 大端写入、小端写入
     */
    @Test
    public void testWrite() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtil.log(byteBuf);

        //大端写入，因为Int占用4个字节，前面补0
        byteBuf.writeInt(5);
        ByteBufUtil.log(byteBuf);

        //小端写入，因为Int占用4个字节，后面补0
        byteBuf.writeIntLE(6);
        ByteBufUtil.log(byteBuf);
    }

    /**
     * 读取
     */
    @Test
    public void testRead() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        //指针会移动
        byteBuf.readByte();
        ByteBufUtil.log(byteBuf);


        byteBuf.markReaderIndex();
        byteBuf.readByte();
        ByteBufUtil.log(byteBuf);
        //回到原来
        byteBuf.resetReaderIndex();
        ByteBufUtil.log(byteBuf);
    }
}
