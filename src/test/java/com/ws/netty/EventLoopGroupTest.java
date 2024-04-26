/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty;


import com.ws.base.TestBase;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author wangshun
 * @date 2024-04-18
 * @see
 * @since 1.0.0
 */
@Slf4j
public class EventLoopGroupTest extends TestBase {

    @Test
    public void testAvailableProcessors() {
        System.out.println(NettyRuntime.availableProcessors());
    }

    @Test
    public void test() {
        //IO事件，普通任务，定时任务
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        //普通任务，定时任务
        //EventLoopGroup defalutEventLoopGroup = new DefaultEventLoop();
        System.out.println((eventLoopGroup.next()));
        System.out.println((eventLoopGroup.next()));
        System.out.println((eventLoopGroup.next()));
        System.out.println((eventLoopGroup.next()));
    }

    @Test
    public void testExecute() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        eventLoopGroup.next().submit(() -> {
            log.debug("hello");
        });
        log.debug("main");
    }

    @Test
    public void testScheduleAtFixedRate() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(2);
        eventLoopGroup.next().scheduleAtFixedRate(() -> {
            log.debug("schedule->out");
        }, 0, 1, TimeUnit.SECONDS);
        log.debug("main");
    }
}
