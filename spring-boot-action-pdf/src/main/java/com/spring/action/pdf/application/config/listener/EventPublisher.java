package com.spring.action.pdf.application.config.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2017/5/13.
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publish(String msg){
        applicationContext.publishEvent(new Event(this,msg));
    }

}
