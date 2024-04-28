/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import com.ws.base.TestBase;
import com.ws.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
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

    /**
     * UnpooledHeapByteBuf使用的JVM内存，只需等待JVM回收内存即可
     * UnpooledDirectByteBuf,需要特殊方法回收内存
     * PooledDirectByteBuf池化机制,需要更加复杂的规则来回收内存
     * <p>
     * Netty采用计数器来控制回收,每个ByteBuf都实现了ReferenceCounted接口，当计数器为0时这时即便ByteBuf对象还在但是各个方法都无法正常使用
     * TailContext尾部会释放内存，HeadContext头部会释放内存（但是中间传下去不是ByteBuf了需要最后处理的Handler释放）
     */
    @Test
    public void test() {

    }

    /**
     * 逻辑上切片
     */
    @Test
    public void testSlice() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtil.log(byteBuf);

        //不允许后面追加数据，否则报错
        ByteBuf b1 = byteBuf.slice(0, 2);
        ByteBufUtil.log(b1);
        ByteBuf b2 = byteBuf.slice(2, 2);
        ByteBufUtil.log(b2);


        //修改
        b1.setByte(0, 'a');
        ByteBufUtil.log(b1);
        ByteBufUtil.log(byteBuf);

        //原始ByteBuf释放，切片报错，可以retain()计数器加1
        b1.retain();
        byteBuf.release();
        ByteBufUtil.log(b1);
    }

    /**
     * 地址引用
     */
    @Test
    public void testDuplicate() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtil.log(byteBuf);

        ByteBuf b1 = byteBuf.duplicate();
        //修改
        b1.setByte(0, 'a');
        ByteBufUtil.log(byteBuf);
        ByteBufUtil.log(b1);
    }

    /**
     * 拷贝
     */
    @Test
    public void testCopy() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtil.log(byteBuf);
        ByteBuf b1 = byteBuf.copy();
        //修改
        b1.setByte(0, 'a');
        ByteBufUtil.log(byteBuf);
        ByteBufUtil.log(b1);
    }


    /**
     * 多个小的合成一个大的
     */
    @Test
    public void testCompositeByteBuf() {
        ByteBuf b1 = ByteBufAllocator.DEFAULT.buffer();
        b1.writeBytes(new byte[]{1, 2});
        ByteBuf b2 = ByteBufAllocator.DEFAULT.buffer();
        b2.writeBytes(new byte[]{3, 4});
        CompositeByteBuf byteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBuf.addComponents(b1, b2);
        ByteBufUtil.log(byteBuf);

    }
}
