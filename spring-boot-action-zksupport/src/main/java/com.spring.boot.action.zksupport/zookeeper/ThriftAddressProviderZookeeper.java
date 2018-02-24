package com.spring.boot.action.zksupport.zookeeper;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fgm on 2017/5/21.
 */
public class ThriftAddressProviderZookeeper implements ThriftAddressProvider,InitializingBean,Closeable{

    private Logger logger= LoggerFactory.getLogger(getClass());

    //注册服务
    private String service;
    //服务版本号
    private String version="1.0.0";
    //zk客户端
    private CuratorFramework zkClient;
    //避免 zk还没连接上,就去调用服务
    private CountDownLatch countDownLatch=new CountDownLatch(1);

    private PathChildrenCache cachedPath;
    //保存当前provider 锁接触过得地址记录,当zookeeper集群故障时,可以从trace中获取地址
    private Set<String> trace=new HashSet();
    private final List<InetSocketAddress> container=new ArrayList();
    private Queue<InetSocketAddress> inner=new LinkedList();
    private Object lock=new Object();

    private static final int  DEFAULT_WEIGHT=1;

    public ThriftAddressProviderZookeeper(){

    }
    public ThriftAddressProviderZookeeper(CuratorFramework zkClient){
        this.zkClient=zkClient;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        if(zkClient.getState()== CuratorFrameworkState.LATENT){
            zkClient.start();
        }
        buildChildrenCache(zkClient,getServicePath(),true);
        cachedPath.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        countDownLatch.await();


    }

    private void buildChildrenCache(CuratorFramework zkClient, String servicePath, final boolean cacheData) {
        cachedPath=new PathChildrenCache(zkClient,servicePath,cacheData);
        cachedPath.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type eventType=event.getType();
                switch(eventType){
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
                cachedPath.rebuild();//不会重新触发childEvent?
                rebuild();
                countDownLatch.countDown();
            }

            protected void rebuild() throws Exception {
                //这是所有节点的信息
                List<ChildData> children= cachedPath.getCurrentData();
                if(children==null||children.isEmpty()){
                    container.clear();
                    logger.error("thrift rpc-cluster error...");
                    return;
                }
                List<InetSocketAddress> current= Lists.newArrayList();
                String path=null;
                String serverName=getServicePath();
                System.out.println("get serverName:"+serverName);
                for(ChildData data:children){
                    path=data.getPath();
                    logger.info("get path:"+path);
                    path=path.substring(serverName.length()+1);
                    logger.info("get serverAddress:"+path);
                    String address=new String(path.getBytes(),"utf-8");
                    current.addAll(transfer(address));
                    trace.add(address);
                }
                Collections.shuffle(current);
                synchronized (lock){
                    container.clear();
                    container.addAll(current);
                    inner.clear();
                    inner.addAll(current);
                }


            }

        });



    }
    /**
     * @description 让配置的服务器权重生效
     */
    private List<InetSocketAddress> transfer(String address) {
        String []hostname=address.split(":");
        Integer weight=DEFAULT_WEIGHT;
        if(hostname.length == 3){
            weight=Integer.valueOf(hostname[2]);
        }
        String ip=hostname[0];
        Integer port=Integer.valueOf(hostname[1]);
        List<InetSocketAddress> result=Lists.newArrayList();
        for(int i=0;i<weight;i++){
            result.add(new InetSocketAddress(ip,port));
        }
        return result;
    }

    private String getServicePath(){
        return "/"+service+"/"+version;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public List<InetSocketAddress> findAddressList() {
        return Collections.unmodifiableList(container);
    }

    @Override
    public synchronized InetSocketAddress selector() {
        if(inner.isEmpty()){
            if(!container.isEmpty()){
                inner.addAll(container);
            }else if(!trace.isEmpty()){
                synchronized (lock){
                    for(String hostname:trace){
                        container.addAll(transfer(hostname));
                    }
                    Collections.shuffle(container);
                    inner.addAll(container);
                }
            }
        }
        return inner.poll();
    }

    @Override
    public void close() {
        try {
            cachedPath.close();
            zkClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public CuratorFramework getZkClient() {
        return zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }


}
