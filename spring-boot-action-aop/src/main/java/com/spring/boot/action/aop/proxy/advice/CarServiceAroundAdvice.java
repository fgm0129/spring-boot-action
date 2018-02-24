package com.spring.boot.action.aop.proxy.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by fgm on 2018/2/18.
 */
public class CarServiceAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("aroundAdvice before around execute target object...");
        String methodName = invocation.getMethod().getName();  //得到方法名
        String targetClassName = invocation.getClass().getName();//得到调用类名
        System.out.println(targetClassName+"."+methodName+"()");
        Object result = invocation.proceed(); //调用横切点，即真实操作
        System.out.println("aroundAdvice after around execute target object...");
        return result;
    }
}
