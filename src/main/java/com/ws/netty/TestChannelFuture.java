/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangshun
 * @date 2024-04-26
 * @see
 * @since 1.0.0
 */
@Slf4j
public class TestChannelFuture {


    public static void main(String[] args) throws Exception {
        //异步处理结果
        EventLoopGroup group = new NioEventLoopGroup(2);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        public void initChannel(NioSocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入解码器
                            pipeline.addLast(new StringDecoder());
                            //加入编码器
                            pipeline.addLast(new StringEncoder());
                        }
                    });

            //带有future和promise的类型，都是和异步方法配套使用来处理结果
            //方式1：阻塞等待链接完成在处理
            /*
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
            Channel channel = future.channel();
            log.debug("{}", channel);
            channel.writeAndFlush("enenenen");
            */


            //方法2：异步调用

            ChannelFuture future = bootstrap.connect("127.0.0.1", 9999);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    Channel channel = future.channel();
                    log.debug("{}", channel);
                    channel.writeAndFlush("hahah");
                }
            });

        } finally {
            //异步处理这个不能关！
            //group.shutdownGracefully();
        }
    }
}
