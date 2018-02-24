package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class Zookeeper_GetChildren_API_Sync_Usage implements Watcher {


    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
    private static String server="127.0.0.1:2181";
    private static ZooKeeper zk=null;
    public static void main(String[] args) throws Exception {
        String path="/zk-book";
        zk=new ZooKeeper(server,5000,new Zookeeper_GetChildren_API_Sync_Usage());
        connectedSemaphore.await();


        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        List<String>  childrenList=zk.getChildren(path,true);
        System.out.println(childrenList);

        zk.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);




    }


    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected==event.getState()){
            if(Event.EventType.None==event.getType()&&null ==event.getPath()){
                connectedSemaphore.countDown();
            }else if(event.getType()==Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet Child :"+zk.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
