package com.spring.boot.action.aop.proxy.aspect;

import com.spring.boot.action.aop.proxy.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by fgm on 2018/2/19.
 */
@Component
@Aspect
public class LogAspect {


    @Before("execution(* com.spring.boot.action.aop.proxy.*.say(..))")
    public void Log(){
        System.out.println("LogAspect:Spring AOP pointcutName");
    }

    /**
     * @description 通过注解获取运行时信息
     * 简化切面定义，Around注解结合annotation注解和自定义注解，形成切面
     * 动态获取注解上定义的信息
     *
     */
    @Around("@annotation(com.spring.boot.action.aop.proxy.annotation.Log)")
    public Object logAspectAnnotation(ProceedingJoinPoint pjp) throws Throwable{

        Signature signature=pjp.getSignature();
        MethodSignature methodSignature=(MethodSignature)signature;
        Method method=methodSignature.getMethod();

        if(method.isAnnotationPresent(Log.class)){
            Log log=method.getAnnotation(Log.class);
            String logType=log.type();
            System.out.println(logType+": Spring AOP pointcutAnnotation");
        }
       return  pjp.proceed();


    }

}
