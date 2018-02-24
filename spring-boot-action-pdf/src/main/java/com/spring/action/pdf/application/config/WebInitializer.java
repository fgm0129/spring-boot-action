package com.spring.action.pdf.application.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by fgm on 2017/5/13.
 */
@Slf4j
public class WebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("servlet  init begin.....");
        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        log.info("servlet  init finish.....");
    }
}
