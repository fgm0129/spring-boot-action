package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class Zookeeper_Create_API_Async_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
    private static String server="127.0.0.1:2181";

    public static void main(String[] args) throws Exception{

        ZooKeeper zooKeeper=new ZooKeeper(server,5000,new Zookeeper_Create_API_Async_Usage());
        connectedSemaphore.await();
        zooKeeper.create("/zk-test-elpheral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallback(),"I am context");
        zooKeeper.create("/zk-test-elpheral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallback(),"I am context");
        zooKeeper.create("/zk-test-elpheral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),"I am context");
        Thread.sleep(Integer.MAX_VALUE);


    }


    @Override
    public void process(WatchedEvent event) {

        if(Event.KeeperState.SyncConnected==event.getState()){
            connectedSemaphore.countDown();
        }
    }

    private static class IStringCallback implements AsyncCallback.StringCallback {


        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result: ["+rc+","+path+","+ctx+",real path name: "+name);
        }
    }
}
