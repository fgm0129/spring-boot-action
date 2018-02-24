package com.spring.action.web;

import com.spring.action.web.interceptor.MyMessageConverter;
import com.spring.action.web.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by fgm on 2017/4/3.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.action.web")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/scripts/");
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Bean
    public RequestInterceptor interceptor(){
        return new RequestInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor());
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/hello").setViewName("/index");
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");

    }

    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }




}
