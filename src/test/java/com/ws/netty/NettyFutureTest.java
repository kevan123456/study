/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import com.ws.base.TestBase;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
@Slf4j
public class NettyFutureTest extends TestBase {

    @Test
    public void test() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        EventLoop eventLoop = eventLoopGroup.next();
        Future<Integer> future = eventLoop.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("计算中。。。");
                Thread.sleep(1000);
                return 50;
            }
        });
        log.debug("等待结果");
        log.debug("结果是{}", future.get());
    }
}
