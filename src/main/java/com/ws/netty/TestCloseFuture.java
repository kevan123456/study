/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
@Slf4j
public class TestCloseFuture {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        //加入netty日志处理类
                        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 9999).channel();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Scanner scanner = new Scanner(System.in);
                        while (true) {
                            String line = scanner.nextLine();
                            if ("q".equals(line)) {
                                channel.close();
                                break;
                            }
                            channel.writeAndFlush(line);
                        }
                    }
                }
                , "input").start();
        ChannelFuture closeFuture = channel.closeFuture();
        log.debug("wait close");
        //方式1：同步处理关闭
        /*
        closeFuture.sync();
        log.debug("closed");
        */
        //方式2：异步处理关闭
        closeFuture.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                log.debug("closed");
                group.shutdownGracefully();
            }
        });

    }
}
