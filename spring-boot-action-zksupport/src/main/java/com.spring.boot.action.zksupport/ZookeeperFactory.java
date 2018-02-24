package com.spring.boot.action.zksupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by fgm on 2017/5/21.
 */
public class ZookeeperFactory implements FactoryBean<CuratorFramework> {

    private String zkHosts;
    private int sessionTimeout=30000;
    private int connectionTimeout=30000;

    private boolean singleton=true;
    private CuratorFramework zkClient;
    //全局path 前缀 ,用来区分不同的应用
    private String namespace;
    private final static String ROOT="rpc";

    public String getZkHosts() {
        return zkHosts;
    }

    public void setZkHosts(String zkHosts) {
        this.zkHosts = zkHosts;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public CuratorFramework getZkClient() {
        return zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public CuratorFramework getObject() throws Exception {
       if(singleton){
           if(zkClient==null){
               zkClient=create();
               zkClient.start();
           }
           return zkClient;
       }
        return create();
    }

    private CuratorFramework create() {
        if(StringUtils.isEmpty(namespace)){
            namespace=ROOT;
        }else{
            namespace=ROOT+"/"+namespace;
        }
        return create(zkHosts,sessionTimeout,connectionTimeout,namespace);



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

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }
    public void close(){
        if(zkClient!=null){
            zkClient.close();
        }
    }
}
