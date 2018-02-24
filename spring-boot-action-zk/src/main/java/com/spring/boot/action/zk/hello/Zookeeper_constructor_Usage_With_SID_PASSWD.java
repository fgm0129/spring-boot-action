package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class Zookeeper_constructor_Usage_With_SID_PASSWD implements Watcher {

    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
    private static String server="127.0.0.1:2181";

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper=new ZooKeeper(server,5000,new Zookeeper_Constructor_Usage_Simple());

        long sessionId=zooKeeper.getSessionId();
        byte []passwd=zooKeeper.getSessionPasswd();

        //use illegal sessionId and sessionPassWd
        zooKeeper=new ZooKeeper(server,5000,new Zookeeper_Constructor_Usage_Simple(),1l,"test".getBytes());
        Thread.sleep(5000);
        //use correct sessionId and sessionPassWd
        zooKeeper=new ZooKeeper(server,5000,new Zookeeper_Constructor_Usage_Simple(),sessionId,passwd);
        Thread.sleep(Integer.MAX_VALUE);



    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("Received watched event:"+event);
        if(Event.KeeperState.SyncConnected==event.getState()){
            connectedSemaphore.countDown();
        }
    }
}
