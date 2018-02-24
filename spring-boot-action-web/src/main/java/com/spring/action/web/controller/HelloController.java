package com.spring.action.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fgm on 2017/4/3.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "index";
    }





}
