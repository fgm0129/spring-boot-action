package com.spring.boot.action.zksupport.zookeeper;

import com.spring.boot.action.zksupport.ThriftException;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fgm on 2017/5/21.
 */
public class ThriftAddressRegisterZookeeper implements ThriftAddressRegister {

    private Logger logger= LoggerFactory.getLogger(getClass());
    private CuratorFramework zkClient;
    public ThriftAddressRegisterZookeeper(){

    }
    public ThriftAddressRegisterZookeeper(CuratorFramework zkClient){
        this.zkClient=zkClient;
    }

    public CuratorFramework getZkClient() {
        return zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void register(String service, String version, String address) {
        if(zkClient.getState()== CuratorFrameworkState.LATENT){
            zkClient.start();
        }
        if(StringUtils.isEmpty(version)){
           version="1.0.0";
        }
        try {
            String path="/"+service+"/"+version+"/"+address;
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path);
            logger.info("path is register in zookeeper {}",path);
        } catch (Exception e) {
            logger.error("register api address to zookeeper exception{}",e);
            throw  new ThriftException("register api address to zookeeper exception",e);
        }


    }

    public void close(){
        zkClient.close();
    }
}
