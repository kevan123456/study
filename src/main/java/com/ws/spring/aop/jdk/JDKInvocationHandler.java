/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 */
public class JDKInvocationHandler implements InvocationHandler {
    /**
     * 目标对象传进来
     */
    private Object obj;

    public JDKInvocationHandler(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        if ("getById".equals(method.getName())) {
            System.out.println("JDKInvocationHandler前置处理");
            result = method.invoke(obj, args);
            System.out.println("JDKInvocationHandler后置处理");
        } else {
            result = method.invoke(obj, args);
        }
        return result;
    }
}
