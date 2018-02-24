package com.spring.action.web.controller;

import com.spring.action.web.model.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fgm on 2017/4/4.
 */
@Controller
public class ConvertController {

    @RequestMapping(value = "/convert",method = RequestMethod.POST,produces = {"application/x-wisely"})
    public @ResponseBody
    DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }

}
