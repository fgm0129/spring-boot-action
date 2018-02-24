package com.spring.action.pdf.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by fgm on 2017/5/13.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.action.pdf.application")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver(){

        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/classes/static");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/404").setViewName("/404");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceLocation 指的文件存放目录
        // addResourceHandler 指的是对外暴露的访问路劲
        registry.addResourceHandler("/static/**/")
                .addResourceLocations("classpath:/static/**");
    }
}
