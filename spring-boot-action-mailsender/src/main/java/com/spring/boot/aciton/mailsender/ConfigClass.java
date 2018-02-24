package com.spring.boot.aciton.mailsender;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fgm on 2017/12/31.
 */
@Configuration
@ComponentScan(basePackages = "com.spring.action")
@ImportResource(locations={"classpath:spring-context.xml"})
public class ConfigClass {
}
