package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by fgm on 2017/8/19.
 */
public class AuthSample {

    final static String path="/zk-book-auth-test";
    static String server="127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        ZooKeeper zk1=new ZooKeeper(server,5000,null);
        zk1.addAuthInfo("digest","foo:true".getBytes());
        zk1.create(path,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        //权限控制
        ZooKeeper zk2=new ZooKeeper(server,5000,null);
        zk2.addAuthInfo("digest","foo:true".getBytes());
        System.out.println(zk2.getData(path,false,null));

        ZooKeeper zk3=new ZooKeeper(server,5000,null);
        zk3.addAuthInfo("digest","foo:false".getBytes());
        zk3.getData(path,false,null);




    }

}
