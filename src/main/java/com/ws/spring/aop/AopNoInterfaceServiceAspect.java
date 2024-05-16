/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.spring.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 * https://www.cnblogs.com/icemomo/p/16209985.html
 */
@Component
@Aspect
public class AopNoInterfaceServiceAspect {
    @Pointcut("execution(* com.ws.service.AopNoInterfaceService.getById(..))")
    private void pt() {
    }

    @Before("pt()")
    public void before(JoinPoint pjp) {
        Object[] args = pjp.getArgs();
        System.out.println("before参数" + Arrays.toString(args));
        System.out.println("before记录日志");
    }

    @After("pt()")
    public void after() {
        System.out.println("after记录日志");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取连接点方法的参数们
        Object[] args = pjp.getArgs();
        System.out.println("around参数" + Arrays.toString(args));
        // 手动调用连接点方法，返回值就是连接点方法的返回值
        Object ret = pjp.proceed();
        System.out.println("around返回值" + JSON.toJSONString(ret));
        return ret;
    }

    @AfterReturning(value = "pt()", returning = "ret")
    public void afterReturning(Object ret) {
        System.out.println("afterReturning记录日志" + JSON.toJSONString(ret));
    }

    @AfterThrowing(value = "pt()", throwing = "t")
    public void afterThrowing(Throwable t) {
        System.out.println("afterThrowing记录日志" + t);
    }

}
