package com.spring.boot.action.zkserver;

import com.spring.boot.action.api.HelloWordService;
import com.spring.boot.action.api.Request;
import com.spring.boot.action.api.RequestException;
import com.spring.boot.action.api.RequestType;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fgm on 2017/5/19.
 */
public class HelloWorldServiceImpl implements HelloWordService.Iface {
    @Override
    public String doAction(Request request) throws RequestException, TException {

        String name=request.getName();
        RequestType requestType=request.getType();
        if(StringUtils.isEmpty(name)||requestType==null){
            throw new RequestException();
        }
        System.out.println("Get Request "+request);
        String result = "Hello, " + name;
        if(requestType.getValue()==RequestType.SAY_HELLO.getValue()){
            result+=", Welcome!";
        }else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result+=", Now is "+sdf.format(new Date());
        }
        return result;
    }
}
