/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring.aop.jdk;

import com.ws.service.AopService;
import com.ws.service.impl.AopServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 */
public class JDKDynamicTest {
    public static void main(String[] args) {
        AopService aopService = new AopServiceImpl();
        Class<?> clazz = aopService.getClass();
        JDKInvocationHandler handler = new JDKInvocationHandler(aopService);
        AopService proxy = (AopService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
        String result = proxy.getById(4L);
        System.out.println("result:" + result);

        System.out.println("==============");

        String r2 = proxy.getNameById(4L);
        System.out.println("r2:" + r2);
    }
}
