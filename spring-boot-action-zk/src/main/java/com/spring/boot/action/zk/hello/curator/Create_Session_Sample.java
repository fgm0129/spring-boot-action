package com.spring.boot.action.zk.hello.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by fgm on 2017/8/19.
 */
public class Create_Session_Sample {


    public static void main(String[] args) throws InterruptedException {

        RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);

        //CuratorFramework client= CuratorFrameworkFactory.newClient("127.0.0.1:2181",5000,3000,retryPolicy);

        CuratorFramework client= CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();

        client.start();
        Thread.sleep(Integer.MAX_VALUE);






    }
}
