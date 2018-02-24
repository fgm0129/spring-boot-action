package com.spring.boot.action.zk.hello;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/8/19.
 */
public class GetData_API_Sync_Usage implements Watcher {

    public static CountDownLatch countDownLatch=new CountDownLatch(1);

    private static String server="127.0.0.1:2181";

    private static ZooKeeper zk=null;

    private static Stat state=new Stat();

    public static void main(String[] args) throws Exception {
        String path="/zk-book";
        zk=new ZooKeeper(server,5000,new GetData_API_Sync_Usage());

        countDownLatch.await();

        zk.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println(new String(zk.getData(path,true,state)));

        System.out.println(state.getCzxid()+","+state.getMzxid()+","+state.getVersion());

        zk.setData(path,"123".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);






    }


    @Override
    public void process(WatchedEvent event) {


        if(Event.KeeperState.SyncConnected==event.getState()){
            if(Event.EventType.None==event.getType()){
                countDownLatch.countDown();
            }else if(Event.EventType.NodeDataChanged==event.getType()){
                try {
                    System.out.println(new String(zk.getData(event.getPath(),true,state)));
                    System.out.println(state.getCzxid()+","+state.getMzxid()+","+state.getVersion());

                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }



    }
}
