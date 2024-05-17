/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring.aop.cglib;

import com.ws.service.AopService;
import com.ws.service.impl.AopServiceImpl;

/**
 * @author wangshun
 * @date 2024-05-17
 * @see
 * @since 1.0.0
 */
public class CglibTest {
    public static void main(String[] args) {

        //输出字节码文件，一定要放前面！！
        //因为extends AopServiceImpl,所以不能代理final修饰的方法

        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wangshun/IdeaProjects/myproject/study/target/classes/com/ws/spring/aop/cglib/");
        AopService aopService = (AopService) new MyInterceptor().getProxy(AopServiceImpl.class);
        String result = aopService.getById(9L);
        System.out.println("main:" + result);


    }
}
