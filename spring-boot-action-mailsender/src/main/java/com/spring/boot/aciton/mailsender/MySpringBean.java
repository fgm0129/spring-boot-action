package com.spring.boot.aciton.mailsender;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by fgm on 2018/2/10.
 */
@Component
public class MySpringBean implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
        System.out.println("设置mySpringBean的初始化数据");


    }
}
