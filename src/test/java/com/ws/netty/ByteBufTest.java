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


}
