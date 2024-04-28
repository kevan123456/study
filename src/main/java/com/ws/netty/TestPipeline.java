/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
@Slf4j
public class TestPipeline {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    //.option(ChannelOption.SO_BACKLOG, 1024)
                    //执行顺序 head->h1->h2->h3->h6->h5->h4->tail
                    .childHandler(
                            new ChannelInitializer<NioSocketChannel>() {
                                @Override
                                public void initChannel(NioSocketChannel ch) {
                                    ChannelPipeline pipeline = ch.pipeline();
                                    pipeline.addLast("h1", new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            log.debug("1:");
                                            super.channelRead(ctx, msg);
                                        }
                                    });
                                    pipeline.addLast("h2", new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            log.debug("2:");
                                            super.channelRead(ctx, msg);
                                        }
                                    });
                                    
                                    pipeline.addLast("h4-1", new ChannelOutboundHandlerAdapter() {
                                        @Override
                                        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                            log.debug("4-1:");
                                            super.write(ctx, msg, promise);
                                        }
                                    });
                                    pipeline.addLast("h3", new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            log.debug("3:");
                                            super.channelRead(ctx, msg);
                                            //写数据才会触发ChannelOutboundHandler！，从尾部开始找出栈处理器
                                            //ch.writeAndFlush("1123");
                                            //从当前的前一个开始找出栈处理器
                                            ctx.writeAndFlush("321");
                                        }
                                    });


                                    pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                                        @Override
                                        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                            log.debug("4:");
                                            super.write(ctx, msg, promise);
                                        }
                                    });
                                    pipeline.addLast("h5", new ChannelOutboundHandlerAdapter() {
                                        @Override
                                        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                            log.debug("5:");
                                            super.write(ctx, msg, promise);
                                        }
                                    });
                                    pipeline.addLast("h6", new ChannelOutboundHandlerAdapter() {
                                        @Override
                                        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                            log.debug("6:");
                                            super.write(ctx, msg, promise);
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
