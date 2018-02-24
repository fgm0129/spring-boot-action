package com.spring.boot.action.zkserver.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * Created by fgm on 2017/5/25.
 */
public class ZookeeperListener implements InitializingBean{

    private CuratorFramework zkClient;

    private static final String ROOT="/rpc";

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        zkClient.checkExists().creatingParentContainersIfNeeded().forPath(ROOT);
        TreeCache cache = new TreeCache(zkClient, ROOT);
        TreeCacheListener listener =new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {

                String namespace=curatorFramework.getNamespace();
                TreeCacheEvent.Type eventType=event.getType();
                String  path=(null != event.getData() )? event.getData().getPath() : null;

                GetDataBuilder getDataBuilder=curatorFramework.getData();






                System.out.println( "命名空间："+namespace+"事件类型：" + eventType +  " | 路径："+path);
            }
        };
        cache.getListenable().addListener(listener);
        cache.start();

    }


}
