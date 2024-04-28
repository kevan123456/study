/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

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
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

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
                                    //LTC解决半包粘包问题
                                    pipeline.addLast(new LengthFieldBasedFrameDecoder(100, 0, 2, 0, 0));

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
