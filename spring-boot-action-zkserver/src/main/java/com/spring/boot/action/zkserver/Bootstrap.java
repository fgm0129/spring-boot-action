package com.spring.boot.action.zkserver;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fgm on 2017/5/21.
 * thrift 服务端启动
 *
 */
public class Bootstrap {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-context-thrift-server.xml");
        ac.start();
        System.out.println("服务已经启动。。。。");
    }


}
