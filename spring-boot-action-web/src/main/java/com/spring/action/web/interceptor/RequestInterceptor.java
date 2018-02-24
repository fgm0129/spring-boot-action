package com.spring.action.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fgm on 2017/4/4.
 */
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime=System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime=(long)request.getAttribute("startTime");
        long endTime=System.currentTimeMillis();
        log.info("本次请求耗时:{}",(endTime-startTime)+"ms");
        request.setAttribute("handlingTime",endTime-startTime);
    }

}
