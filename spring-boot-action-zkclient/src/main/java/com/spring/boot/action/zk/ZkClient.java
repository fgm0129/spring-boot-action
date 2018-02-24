package com.spring.boot.action.zk;

import com.spring.boot.action.api.HelloWordService;
import com.spring.boot.action.api.Request;
import com.spring.boot.action.api.RequestType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fgm on 2017/5/21.
 */
public class ZkClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context-thrift-client.xml");
        HelloWordService.Iface helloService = (HelloWordService.Iface) context.getBean("helloWorldService");
        try {
            Request request=new Request();
            request.setType(RequestType.SAY_HELLO).setName("fangguangming");
            System.out.println(helloService.doAction(request));
            System.out.println("测试成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
