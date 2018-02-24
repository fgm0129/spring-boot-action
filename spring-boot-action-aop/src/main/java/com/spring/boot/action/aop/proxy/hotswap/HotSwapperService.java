package com.spring.boot.action.aop.proxy.hotswap;

import com.spring.boot.action.aop.ApplicationContextHolder;
import com.spring.boot.action.aop.BeanFactoryHolder;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2018/2/23.
 */
@Component
public class HotSwapperService {


    /***
     * 通过applicationContext来获取并替换对象
     */
    public void swap(){
        Student newStudent=new Student("fangguangming",28,"男");
        ApplicationContext applicationContext=ApplicationContextHolder.getApplicationContext();
        HotSwappableTargetSource targetSource=(HotSwappableTargetSource)applicationContext.getBean("swapper");
        targetSource.swap(newStudent);
    }
    /**
     * 通过beanFactory来获取并替换对象
     */
    public void swapByBeanFactory(){
         Student newStudent=new Student("fangguangming",28,"男");
         BeanFactory beanFactory=BeanFactoryHolder.getBeanFactory();
         HotSwappableTargetSource targetSource=(HotSwappableTargetSource)beanFactory.getBean("swapper");
         targetSource.swap(newStudent);
    }



}
