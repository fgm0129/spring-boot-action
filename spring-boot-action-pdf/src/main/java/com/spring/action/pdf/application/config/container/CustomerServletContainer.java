package com.spring.action.pdf.application.config.container;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by fgm on 2017/5/13.
 */
@Component
public class CustomerServletContainer implements EmbeddedServletContainerCustomizer {
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8888);
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404"));
        container.setSessionTimeout(10, TimeUnit.MINUTES);
    }
}
