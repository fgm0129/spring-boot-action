package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/18.
 */
public class Zookeeper_Constructor_Usage_Simple implements Watcher {

    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);


    public static void main(String[] args) throws IOException {

        ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181",5000,new Zookeeper_Constructor_Usage_Simple());

        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper session established.");


    }


    @Override
    public void process(WatchedEvent event) {

        System.out.println("Received watched event :"+event);
        if(Event.KeeperState.SyncConnected==event.getState()){
            connectedSemaphore.countDown();
        }



    }
}
