package com.spring.action.pdf.application.config.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by fgm on 2017/5/13.
 */
public class Event extends ApplicationEvent {

    private String msg;



    public Event(Object source,String msg) {
        super(source);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
