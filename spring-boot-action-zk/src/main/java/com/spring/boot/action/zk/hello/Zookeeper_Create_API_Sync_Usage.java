package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class Zookeeper_Create_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemphore=new CountDownLatch(1);

    private static String server="127.0.0.1:2181";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper=new ZooKeeper(server,5000,new Zookeeper_Create_API_Sync_Usage());
        connectedSemphore.await();
        String path1=zooKeeper.create("/zk-test-ephemeral-","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("Success create znode: "+path1);

        String path2=zooKeeper.create("/zk-test-ephemeral-","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("Success create znode: "+path2);

    }

    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected==event.getState()){
            connectedSemphore.countDown();
        }

    }
}
