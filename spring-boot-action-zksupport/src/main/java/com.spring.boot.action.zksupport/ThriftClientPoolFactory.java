package com.spring.boot.action.zksupport;

import com.spring.boot.action.zksupport.zookeeper.ThriftAddressProvider;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by fgm on 2017/5/21.
 * thrift-client 客户端对象连接池
 *
 */
public class ThriftClientPoolFactory extends BasePoolableObjectFactory<TServiceClient>{
    private Logger logger= LoggerFactory.getLogger(getClass());

    private final ThriftAddressProvider serverAddressProvider;
    private final TServiceClientFactory<TServiceClient> clientFactory;
    private PoolOperationCallBack callback;
    interface PoolOperationCallBack {
        // 销毁client之前执行
        void destroy(TServiceClient client);

        // 创建成功是执行
        void make(TServiceClient client);
    }
    protected ThriftClientPoolFactory(ThriftAddressProvider addressProvider, TServiceClientFactory<TServiceClient> clientFactory) throws Exception {
        this.serverAddressProvider = addressProvider;
        this.clientFactory = clientFactory;
    }

    protected ThriftClientPoolFactory(ThriftAddressProvider addressProvider, TServiceClientFactory<TServiceClient> clientFactory,
                                      PoolOperationCallBack callback) throws Exception {
        this.serverAddressProvider = addressProvider;
        this.clientFactory = clientFactory;
        this.callback = callback;
    }

    @Override
    public void destroyObject(TServiceClient client) throws Exception {
        if (callback != null) {
            try {
                callback.destroy(client);
            } catch (Exception e) {
                logger.warn("destroyObject:{}", e);
            }
        }
        logger.info("destroyObject:{}", client);
        TTransport pin = client.getInputProtocol().getTransport();
        pin.close();
        TTransport pout = client.getOutputProtocol().getTransport();
        pout.close();
    }
    @Override
    public boolean validateObject(TServiceClient client) {
        TTransport pin = client.getInputProtocol().getTransport();
        logger.info("validateObject input:{}", pin.isOpen());
        TTransport pout = client.getOutputProtocol().getTransport();
        logger.info("validateObject output:{}", pout.isOpen());
        return pin.isOpen() && pout.isOpen();
    }




    @Override
    public TServiceClient makeObject() throws Exception {

        InetSocketAddress address=serverAddressProvider.selector();
        if(address==null){
            throw new ThriftException("No Provider available for remote api");
        }
        TSocket tSocket=new TSocket(address.getHostName(),address.getPort());
        TTransport transport=new TFastFramedTransport(tSocket);
        TProtocol protocol=new TBinaryProtocol(transport);
        TServiceClient client=this.clientFactory.getClient(protocol);
        transport.open();
        if(callback!=null){
            try {
                callback.make(client);
            }catch (Exception e){
                logger.warn("make Object:{}",e);
            }
        }

        //这个client可以直接拿来调用对应实现方法的
        return client;
    }
}
