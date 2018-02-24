package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class Exist_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch=new CountDownLatch(1);

    private static String server="127.0.0.1:2181";

    private static ZooKeeper zk=null;

    public static void main(String[] args) throws Exception {
        String path="/zk-book";

        zk=new ZooKeeper(server,5000,new Exist_API_Sync_Usage());
        countDownLatch.await();
        zk.exists(path,true);

        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        zk.setData(path,"123".getBytes(),-1);
        zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        zk.delete(path+"/c1",-1);

        zk.delete(path,-1);

       Thread.sleep(Integer.MAX_VALUE);






    }


    @Override
    public void process(WatchedEvent event) {

        try{
            if(Event.KeeperState.SyncConnected==event.getState()){
                if(event.getType()== Event.EventType.None){
                    countDownLatch.countDown();
                }else if(event.getType()== Event.EventType.NodeCreated){
                    System.out.println("Node ("+event.getPath()+") Created");
                    zk.exists(event.getPath(),true);
                }else if(event.getType()== Event.EventType.NodeDeleted){
                    System.out.println("Node ("+event.getPath()+") Deleted");
                    zk.exists(event.getPath(),true);
                }else if(event.getType()== Event.EventType.NodeDataChanged){
                    System.out.println("Node ("+event.getPath()+") NodeChanged");
                    zk.exists(event.getPath(),true);
                }
            }

        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }
}
