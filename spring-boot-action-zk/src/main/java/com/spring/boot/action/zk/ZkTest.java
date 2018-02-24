package com.spring.boot.action.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by fgm on 2017/5/15.
 */
public class ZkTest {

    private static final String CONNECT_STRING = "127.0.0.1:2181";
    private static final int SESSION_TIMEOUT = 3000;

    public static void main(String[] args) throws Exception {

        // 定义一个监控所有节点变化的Watcher
        Watcher allChangeWatcher = new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("**watcher receive WatchedEvent** changed path: " + event.getPath()
                        + "; changed type: " + event.getType().name());
            }
        };

        // 初始化一个与ZK连接。三个参数：
        // 1、要连接的服务器地址，"IP:port"格式；
        // 2、会话超时时间
        // 3、节点变化监视器
        ZooKeeper zk = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, allChangeWatcher);
        String node="/data/myzk1";

        // 新建节点。四个参数：1、节点路径；2、节点数据；3、节点权限；4、创建模式
        zk.create(node, "fangguaungming".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("create new node "+node);

        // 判断某路径是否存在。两个参数：1、节点路径；2、是否监控（Watcher即初始化ZooKeeper时传入的Watcher）
        Stat beforSstat = zk.exists(node, true);
        System.out.println("Stat of "+node+"before change : " + beforSstat.toString());

        // 修改节点数据。三个参数：1、节点路径；2、新数据；3、版本，如果为-1，则匹配任何版本
        Stat afterStat = zk.setData(node, "clf".getBytes(), -1);
        System.out.println("Stat of "+node+" after change: " + afterStat.toString());

        // 获取所有子节点。两个参数：1、节点路径；2、是否监控该节点
        List<String> children = zk.getChildren("/", true);
        System.out.println("children of path '/': " + children.toString());

        // 获取节点数据。三个参数：1、节点路径；2、书否监控该节点；3、版本等信息可以通过一个Stat对象来指定
        byte[] nameByte = zk.getData(node, true, null);
        String name = new String(nameByte, "UTF-8");
        System.out.println("get data from "+node+": " + name);

        // 删除节点。两个参数：1、节点路径；2、 版本，-1可以匹配任何版本，会删除所有数据
        zk.delete(node, -1);
        System.out.println("delete "+node);

        zk.close();
    }



}