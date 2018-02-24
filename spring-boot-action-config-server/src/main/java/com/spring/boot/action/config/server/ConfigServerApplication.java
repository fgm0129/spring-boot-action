package com.spring.boot.action.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by fgm on 2017/12/5.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }

}
