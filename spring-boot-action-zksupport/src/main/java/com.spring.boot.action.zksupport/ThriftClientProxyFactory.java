package com.spring.boot.action.zksupport;

import com.spring.boot.action.zksupport.zookeeper.ThriftAddressProvider;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by fgm on 2017/5/21.
 * 客户端代理工厂
 *
 *
 */
public class ThriftClientProxyFactory implements FactoryBean,InitializingBean,Closeable {
    private Logger logger= LoggerFactory.getLogger(getClass());
    private Integer maxActive=32;//最大活跃连接数
    private Integer idelTime=180000;//ms default 3min 链接空闲时间,-1 关闭空闲检测
    private ThriftAddressProvider serverAddressProvider;
    private Object proxyClient;
    private Class<?> objectClass;
    private GenericObjectPool<TServiceClient> pool;

    private ThriftClientPoolFactory.PoolOperationCallBack callBack= new ThriftClientPoolFactory.PoolOperationCallBack(

    ) {
        @Override
        public void destroy(TServiceClient client) {
            logger.info("destroy!");
        }
        @Override
        public void make(TServiceClient client) {
            logger.info("create!");
        }
    };

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public void setIdelTime(Integer idelTime) {
        this.idelTime = idelTime;
    }

    public ThriftAddressProvider getServerAddressProvider() {
        return serverAddressProvider;
    }

    public void setServerAddressProvider(ThriftAddressProvider serverAddressProvider) {
        this.serverAddressProvider = serverAddressProvider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        objectClass=classLoader.loadClass(serverAddressProvider.getService()+"$Iface");

        Class<TServiceClientFactory<TServiceClient>> fi=(Class<TServiceClientFactory<TServiceClient>>) classLoader.loadClass(serverAddressProvider.getService() + "$Client$Factory");
        TServiceClientFactory<TServiceClient> clientFactory = fi.newInstance();

        ThriftClientPoolFactory clientPool=new ThriftClientPoolFactory(serverAddressProvider,clientFactory,callBack);
        pool=new GenericObjectPool(clientPool,makePoolConfig());

        InvocationHandler handler=makeProxyHandler();
        proxyClient= Proxy.newProxyInstance(classLoader,new Class[]{objectClass},handler);
    }

    private InvocationHandler makeProxyHandler() {
        ThriftClientProxy handler=new ThriftClientProxy(pool);
        return handler;
    }

    private GenericObjectPool.Config makePoolConfig() {
        GenericObjectPool.Config poolConfig=new GenericObjectPool.Config();
        poolConfig.maxActive=maxActive;
        poolConfig.maxIdle=1;
        poolConfig.minIdle=0;
        poolConfig.minEvictableIdleTimeMillis=idelTime;
        poolConfig.timeBetweenEvictionRunsMillis=idelTime*2L;
        poolConfig.testOnBorrow=true;
        poolConfig.testOnReturn=false;
        poolConfig.testWhileIdle=false;
        return poolConfig;
    }

    @Override
    public void close() throws IOException {
        if(pool!=null){
            try{
                pool.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(serverAddressProvider!=null){
            serverAddressProvider.close();
        }
    }

    @Override
    public Object getObject() throws Exception {
        return proxyClient;
    }

    @Override
    public Class<?> getObjectType() {
        return objectClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


}
