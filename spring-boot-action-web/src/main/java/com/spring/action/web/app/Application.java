package com.spring.action.web.app;

import com.wisely.spring.boot.hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fgm on 2017/4/4.
 */
@RestController
@SpringBootApplication
public class Application {
   // @Value("${app.name}")
    private String appName;
    //@Value("${app.author}")
    private String appAuthor;

    @Autowired
    private HelloService helloService;

//    @RequestMapping("/")
//    public String index(){
//        return "Hello Spring Boot,appName:"+appName+", appAuthor:"+appAuthor;
//    }
    @RequestMapping("/hello")
    public String hello(){
        return helloService.sayHello();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
