package com.spring.boot.action.aop.proxy;

import com.spring.boot.action.aop.proxy.annotation.Log;
import org.springframework.stereotype.Service;

/**
 * Created by fgm on 2018/2/19.
 */
@Service
public class BusinessService {

    public void say(){
        System.out.println("say about Business Code");
    }


    @Log(type = "BUSINESS_SERVICE")
    public void hello(){
        System.out.println("hello about Business Code");
    }
}
