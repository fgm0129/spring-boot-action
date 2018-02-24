package com.spring.boot.action.aop.proxy.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by fgm on 2018/2/18.
 */
public class CarServiceAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("after execute target and returnValue is "+returnValue);
        String methodName = method.getName();  //得到方法名
        String targetClassName = target.getClass().getName();//得到调用类名
        System.out.println(targetClassName+"."+methodName+"()");
        System.out.println("after execute target object...");
    }
}
