package com.spring.boot.action.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fgm on 2018/2/15.
 */
@Configuration
@ComponentScan(basePackages = "com.spring.action")
@ImportResource(locations={"classpath:spring-context.xml"})
public class ApplicationConfig {
}
