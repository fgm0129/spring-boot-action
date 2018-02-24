package com.spring.action.web.controller;

import com.spring.action.web.model.DemoObj;
import com.spring.action.web.model.FormObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fgm on 2017/4/3.
 */
@Controller
@RequestMapping("/anno")
public class DemoController {


    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String index(HttpServletRequest request){

        return "url:"+request.getRequestURL()+" can access";
    }


    @RequestMapping("/pathVar/{str}")
    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){


        return "url:"+request.getRequestURL()+" can access,str="+str;
    }

    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String requestParam(Long id,HttpServletRequest request){

        return "url:"+request.getRequestURL()+"can assess ,id="+id;
    }

    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8")
    public @ResponseBody String obj(DemoObj obj, HttpServletRequest request){

        return "url:"+request.getRequestURL()+" can access,obj id:"+obj.getId()+", obj name:"+obj.getName();
    }
    @RequestMapping(value = "/formObj",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public @ResponseBody String obj(FormObj obj, HttpServletRequest request){

        return "url:"+request.getRequestURL()+" can access,obj name:"+obj.getName()+", obj company:"+obj.getCompany()+", obj country:"+obj.getCountry();
    }


    @RequestMapping(value = {"/name1","/name2"},produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(Long id,HttpServletRequest request){

        return "url:"+request.getRequestURL()+" can access";
    }

    @RequestMapping(value = {"/advice"},produces = "text/plain;charset=UTF-8")
    public @ResponseBody String advice(DemoObj demoObj, @ModelAttribute("msg") String msg){

        throw new IllegalArgumentException("请求参数有误:"+msg);

    }




}
