/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring;

import com.ws.service.AopNoInterfaceService;
import com.ws.service.AopService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 */
public class SpringAOPTest {

    private static final String CONTEXT = "spring-context.xml";

    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONTEXT);
        AopService a = (AopService) applicationContext.getBean("aopService");

        String result = a.getById(2L);
        System.out.println("testGetBean:" + result);


        System.out.println("===================");

        String name = a.getNameById(2L);
        System.out.println("testGetBean:" + name);

    }

    @Test
    public void testNoInterface() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONTEXT);
        AopNoInterfaceService a = (AopNoInterfaceService) applicationContext.getBean("aopNoInterfaceService");

        String result = a.getById(2L);
        System.out.println("testGetBean:" + result);


        System.out.println("===================");

        String name = a.getNameById(2L);
        System.out.println("testGetBean:" + name);

    }
}
