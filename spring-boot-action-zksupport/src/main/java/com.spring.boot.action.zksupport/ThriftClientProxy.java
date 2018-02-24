package com.spring.boot.action.zksupport;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by fgm on 2017/5/21.
 * 客户端、远程服务代理工厂
 *
 */
public class ThriftClientProxy implements InvocationHandler{
    private GenericObjectPool<TServiceClient> pool;

    public ThriftClientProxy(GenericObjectPool<TServiceClient> pool){
        this.pool=pool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        TServiceClient client=pool.borrowObject();

        boolean flag=true;
        try{
            return method.invoke(client,args);
        }catch (Exception e){
            flag=false;
            throw e;
        }finally{
            if(flag){
                pool.returnObject(client);
            }else{
                pool.invalidateObject(client);
            }
        }

    }
}
