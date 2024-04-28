/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;

import com.ws.base.TestBase;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wangshun
 * @date 2024-04-28
 * @see
 * @since 1.0.0
 */
@Slf4j
public class PromiseTest extends TestBase {


    @Test
    public void testGetSuccess() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        EventLoop eventLoop = eventLoopGroup.next();
        Promise<Integer> promise = new DefaultPromise<Integer>(eventLoop);
        eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                log.debug("计算中。。。");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                promise.setSuccess(70);
            }
        });
        log.debug("等待结果");
        log.debug("结果是{}", promise.get());
    }

    @Test
    public void testGetFailue() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        EventLoop eventLoop = eventLoopGroup.next();
        Promise<Integer> promise = new DefaultPromise<Integer>(eventLoop);
        eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                log.debug("计算中。。。");
                try {
                    int i = 10 / 0;
                    Thread.sleep(1000);
                } catch (Exception e) {
                    promise.setFailure(e);
                }

                promise.setSuccess(70);
            }
        });
        log.debug("等待结果");
        log.debug("结果是{}", promise.get());
    }
}
