package com.spring.action.pdf.application.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2017/5/13.
 */
@Slf4j
@Component
public class EventListener implements ApplicationListener<Event> {
    public void onApplicationEvent(Event event) {
        String msg=event.getMsg();
        log.info("receive msg: {}",msg);

    }
}
