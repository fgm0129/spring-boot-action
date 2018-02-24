package com.spring.action.web.interceptor;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fgm on 2017/4/4.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value=Exception.class)
    public ModelAndView exception(Exception ex, WebRequest request){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("errorMessage",ex.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");
    }

    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }


}
