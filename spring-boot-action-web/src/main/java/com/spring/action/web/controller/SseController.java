package com.spring.action.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.spring.action.web.model.ServerInfoObj;
import com.spring.action.web.zookeeper.ZookeeperManager;
import com.spring.action.web.zookeeper.ZookeeperNodeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by fgm on 2017/4/4.
 */
@Controller
public class SseController {

    @Autowired
    private ZookeeperManager zookeeperManager;

    @RequestMapping(value = "/push",produces = "text/event-stream")
    public @ResponseBody
    String  push(){

        final Map<String,List<ServerInfoObj>> result= Maps.newHashMap();
        zookeeperManager.setListener(new ZookeeperNodeListener(){
            @Override
            public void notifyNodeChange(Set<String> serverInfo) {
                result.clear();
                for(String serverPath:serverInfo){
                    ServerInfoObj serverInfoObj=new ServerInfoObj();
                    String []paths=serverPath.split("/");
                    serverInfoObj.setServiceName(paths[1]);
                    serverInfoObj.setVersion(paths[2]);
                    String hostInfo[]=paths[3].split(":");
                    serverInfoObj.setIp(hostInfo[0]);
                    serverInfoObj.setPort(hostInfo[1]);
                    if(hostInfo.length>2){
                        serverInfoObj.setWeight(hostInfo[2]);
                    }else{
                        serverInfoObj.setWeight("1");
                    }
                    if(result.isEmpty()||null==result.get(paths[1])){
                        List<ServerInfoObj> list= Lists.newArrayList();
                        list.add(serverInfoObj);
                        result.put(paths[1],list);
                    }else{
                        result.get(paths[1]).add(serverInfoObj);
                    }

                }
            }
        });
        zookeeperManager.start();

        Random r=new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "data:Testing "+result+"\n\n";

    }

}
