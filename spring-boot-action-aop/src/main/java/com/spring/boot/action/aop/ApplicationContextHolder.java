package com.spring.boot.action.aop;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2018/2/23.
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext=context;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

}
