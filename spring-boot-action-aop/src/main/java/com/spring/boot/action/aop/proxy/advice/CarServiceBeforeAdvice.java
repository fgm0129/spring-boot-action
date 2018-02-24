package com.spring.boot.action.aop.proxy.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by fgm on 2018/2/18.
 */
public class CarServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("before execute target object...");
        String methodName = method.getName();  //得到方法名
        String targetClassName = target.getClass().getName();//得到调用类名
        System.out.println(targetClassName+"."+methodName+"()");
    }
}
