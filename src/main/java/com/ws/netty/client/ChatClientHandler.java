/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wangshun
 * @date 2024-04-18
 * @see
 * @since 1.0.0
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf buf = Unpooled.copiedBuffer("HelloServer".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println(msg.trim());
    }


}
