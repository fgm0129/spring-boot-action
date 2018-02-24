package com.spring.boot.action.aop.proxy.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * Created by fgm on 2018/2/18.
 */
public class CarServiceThrowsAdvice implements ThrowsAdvice {
    /**
     * @description 可以定义多个方法，只要传入的参数是不同异常
     * @param e
     */
    public void afterThrowing(NullPointerException e){
        System.out.print("not load anything goods!");
    }

    /**
     * @description 可以定义多个方法，只要传入的参数是不同异常
     * @param e
     */
    public void afterThrowing(IllegalArgumentException e){
        System.out.print("load a tiger,it's very much dangerous!");
    }
}
