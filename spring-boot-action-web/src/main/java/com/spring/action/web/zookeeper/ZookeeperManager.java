package com.spring.action.web.zookeeper;

import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/5/25.
 */
@Component
public class ZookeeperManager{

    private Logger logger= LoggerFactory.getLogger(getClass());

    private  PathChildrenCache cachedPath;

    private ZookeeperNodeListener listener;

    private static final Set<String> serverInfo=new HashSet();

    private CountDownLatch countDownLatch=new CountDownLatch(1);

    private static final String namespace="rpc/spring.boot";

    private boolean isStart=false;

    public void start(){
        if(isStart){
            logger.info("zkManager 已经启动!");
            return;
        }
        try {
            logger.info("zkManager 初始化");
            initServicePathCache("/");
            isStart=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private   void initServicePathCache(String servicePath) throws Exception {
        CuratorFramework client = create("127.0.0.1:2181",5000,5000,namespace);
        if(client.getState()== CuratorFrameworkState.LATENT){
            client.start();
        }
        boolean cacheData=true;
        cachedPath=new PathChildrenCache(client,servicePath,cacheData);
        cachedPath.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type eventType = event.getType();
                switch (eventType) {
                    case CONNECTION_RECONNECTED:
                        logger.info("Connection is reconnected");
                        break;
                    case CONNECTION_SUSPENDED:
                        logger.info("Connection is suspended.");
                        break;
                    case CONNECTION_LOST:
                        logger.warn("Connection error,waitting..");
                        return;
                    case INITIALIZED:
                        logger.info("Connection init...");
                    default:

                }
                resolve();
                cachedPath.rebuild();
                countDownLatch.countDown();
            }

        });
        cachedPath.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        countDownLatch.await();

    }

    private void resolve() throws Exception {
        List<ChildData> children= cachedPath.getCurrentData();
        if(children==null||children.isEmpty()){
            logger.error("thrift rpc-cluster error...");
            return;
        }
        for(ChildData data:children){
            Stat stat=data.getStat();
            String path=data.getPath();
            logger.info("path======>{}",path);
            if(path!=null&&path.length()>0&&path.indexOf(":")==-1){
                initServicePathCache(path);
            }
            if(path.indexOf(":")>-1){
                serverInfo.add(path);
                listener.notifyNodeChange(serverInfo);
            }
        }
    }

    private static CuratorFramework create(String zkHosts, int sessionTimeout, int connectionTimeout, String namespace) {
        return CuratorFrameworkFactory.builder()
                .connectString(zkHosts)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .canBeReadOnly(true)
                .namespace(namespace)
                .retryPolicy(new ExponentialBackoffRetry(100,Integer.MAX_VALUE))
                .defaultData(null)
                .build();
    }


    public ZookeeperNodeListener getListener() {
        return listener;
    }

    public void setListener(ZookeeperNodeListener listener) {
        this.listener = listener;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
