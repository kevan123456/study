/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author wangshun
 * @date 2024-04-18
 * @see
 * @since 1.0.0
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    //.option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(
                            new ChannelInitializer<NioSocketChannel>() {
                                @Override
                                public void initChannel(NioSocketChannel ch) {
                                    ChannelPipeline pipeline = ch.pipeline();
                                    //加入解码器
                                    //pipeline.addLast("decoder", new StringDecoder());
                                    //加入编码器
                                    //pipeline.addLast("encoder", new StringEncoder());
                                    pipeline.addLast(new ChannelInboundHandlerAdapter() {

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            ByteBuf byteBuf = (ByteBuf) msg;
                                            log.debug("channelRead:" + byteBuf.toString(Charset.forName("utf-8")));
                                            System.out.println("channelRead:" + byteBuf.toString(Charset.forName("utf-8")));
                                        }
                                    });
                                }
                            });
            System.out.println("netty server start");
            ChannelFuture future = serverBootstrap.bind(9999);
            //等待服务端口关闭
            future.channel().closeFuture().sync();
        } finally {
            //bossGroup.shutdownGracefully();
            //workGroup.shutdownGracefully();
            System.out.println("netty server stop");
        }
    }
}
