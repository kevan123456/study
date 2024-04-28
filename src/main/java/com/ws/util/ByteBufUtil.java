/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.util;

import io.netty.buffer.ByteBuf;


/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
public class ByteBufUtil {

    public static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;

        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index: ").append(buffer.writerIndex())
                .append(" capacity: ").append(buffer.capacity())
                .append(io.netty.util.internal.StringUtil.NEWLINE);
        io.netty.buffer.ByteBufUtil.appendPrettyHexDump(buf, buffer);
        System.out.println(buf.toString());
    }
    
}
