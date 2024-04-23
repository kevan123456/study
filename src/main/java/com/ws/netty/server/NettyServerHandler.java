/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.server;

import com.ws.util.DateUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author wangshun
 * @date 2024-04-18
 * @see
 * @since 1.0.0
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + ",上线了" + DateUtils.formatDate() + "\n");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress() + ",上线了" + DateUtils.formatDate() + "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + ",下线了" + DateUtils.formatDate() + "\n");
        System.out.println(channel.remoteAddress() + ",下线了" + DateUtils.formatDate() + "\n");
        System.out.println("channelGroup size" + channelGroup.size() + "\n");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[客户端]" + channel.remoteAddress() + ",发送消息:" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己],发送消息:" + msg + "\n");
            }
        });
    }
}
