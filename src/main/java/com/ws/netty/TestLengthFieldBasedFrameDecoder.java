/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 解决半包粘包问题
 *
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
@Slf4j
public class TestLengthFieldBasedFrameDecoder {
    public static void main(String[] args) throws Exception {
        /**
         * LTC解决半包粘包问题
         * lengthFieldOffset--长度字段偏移量
         * lengthFieldLength--长度字段长度
         * lengthAdjustment--长度字段为基准，还有几个字节是内容
         * initialBytesToStrip--从头剩离几个字节
         */
        LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);

        //固定长度解决半包粘包问题
        //pipeline.addLast(new FixedLengthFrameDecoder(100));

        //换行符解决半包粘包问题
        //pipeline.addLast(new LineBasedFrameDecoder(100));

        //自定义换行符解决半包粘包问题
        /*
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer() ;
        byteBuf.setByte(0,',') ;
        pipeline.addLast(new DelimiterBasedFrameDecoder(100,byteBuf ));
        */

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(lengthFieldBasedFrameDecoder, new LoggingHandler());
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{0, 'c',});
        embeddedChannel.writeInbound(send("hello,word"));
        embeddedChannel.writeInbound(send("hi"));

    }

    private static ByteBuf send(String s) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


}
