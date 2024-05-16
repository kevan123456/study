/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring;

import com.ws.service.CycleA;
import com.ws.service.CycleB;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 循环依赖问题-思考
 *
 * @author wangshun
 * @date 2024-05-13
 * @see
 * @since 1.0.0
 */
public class SpringCycleTest {

    private static final String CONTEXT = "spring-context.xml";

    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONTEXT);
        CycleA a = applicationContext.getBean(CycleA.class);
        System.out.println(a);
        CycleB b = applicationContext.getBean(CycleB.class);
        System.out.println(b);

    }
}
