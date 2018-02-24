package com.spring.boot.action.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2018/2/23.
 */
@Component
public class BeanFactoryHolder implements BeanFactoryAware {

    private static BeanFactory holderBeanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        holderBeanFactory=beanFactory;
    }

    public static BeanFactory getBeanFactory(){
        return holderBeanFactory;
    }

}
