/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring.aop.jdk;

import com.ws.service.AopService;
import com.ws.service.impl.AopServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 */
public class JDKDynamicTest {
    public static void main(String[] args) throws Exception {
        AopService aopService = new AopServiceImpl();
        Class<?> clazz = aopService.getClass();
        JDKInvocationHandler handler = new JDKInvocationHandler(aopService);
        AopService proxy = (AopService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
        String result = proxy.getById(4L);
        System.out.println("result:" + result);

        System.out.println("==============");

        String r2 = proxy.getNameById(4L);
        System.out.println("r2:" + r2);


        //查看字节码
        save(proxy);
        //因为java是单继承，已经继承了proxy类，所以jdk动态代理只能实现接口
    }

    private static void save(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        String name = "$" + clazz.getName();
        byte[] bytes = ProxyGenerator.generateProxyClass("$" + clazz.getName(), new Class[]{clazz});
        // /Users/wangshun/IdeaProjects/myproject/study/target/classes/com/ws/spring/aop/jdk
        OutputStream outputStream = new FileOutputStream("/Users/wangshun/IdeaProjects/myproject/study/target/classes/com/ws/spring/aop/jdk/" + name + ".class");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }
}
