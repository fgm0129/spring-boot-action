package com.spring.boot.action.zksupport;

import com.spring.boot.action.zksupport.zookeeper.ThriftAddressRegister;
import com.spring.boot.action.zksupport.zookeeper.ThriftServerIpResolve;
import com.spring.boot.action.zksupport.zookeeper.ThriftServerIpResolveZookeeper;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.springframework.beans.factory.InitializingBean;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;

/**
 * Created by fgm on 2017/5/21.
 * Thrift服务端、提供服务并注册到zk中
 *
 */
public class ThriftServerFactory implements InitializingBean{

    private Integer port=8299;
    private Integer weight=1;
    private Object service;
    private String version;
    private ThriftAddressRegister thriftAddressRegister;

    private ThriftServerIpResolve thriftServerIpResolve;

    private ServerThread serverThread;


    public void setPort(Integer port) {
        this.port = port;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setThriftAddressRegister(ThriftAddressRegister thriftAddressRegister) {
        this.thriftAddressRegister = thriftAddressRegister;
    }

    public void setThriftServerIpResolve(ThriftServerIpResolve thriftServerIpResolve) {
        this.thriftServerIpResolve = thriftServerIpResolve;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(thriftServerIpResolve==null){
            thriftServerIpResolve=new ThriftServerIpResolveZookeeper();
        }
        String serverIp=thriftServerIpResolve.getServerIp();
        if(StringUtils.isEmpty(serverIp)){
            throw new ThriftException("cant not find rpc ip...");
        }
        String hostname=serverIp+":"+port+":"+weight;
        Class<?> serviceClass=service.getClass();
        Class<?>[] interfaces=serviceClass.getInterfaces();
        if(interfaces.length==0){
            throw new IllegalClassFormatException("api-class should implements Iface 1");
        }
        TProcessor processor=null;
        String serviceName=null;
        for(Class<?> clazz:interfaces){
            String cname=clazz.getSimpleName();
            if(!cname.equals("Iface")){
                continue;
            }
            serviceName=clazz.getEnclosingClass().getName();
            String pname=serviceName+"$Processor";
            try{
                ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
                Class<?> pclass=classLoader.loadClass(pname);
                if(!TProcessor.class.isAssignableFrom(pclass)){
                    continue;
                }
                Constructor<?> constructor=pclass.getConstructor(clazz);
                processor=(TProcessor)constructor.newInstance(service);
                break;
            }catch (Exception e){
                //
                e.printStackTrace();
            }

        }
        if(processor==null){
            throw new IllegalClassFormatException("api-class should implements Iface 2");
        }
        //需要单独的线程,因为serve方法是阻塞的.
        serverThread=new ServerThread(processor,port);
        serverThread.start();
        //注册服务
        if(thriftAddressRegister!=null){
            thriftAddressRegister.register(serviceName,version,hostname);
        }

    }

    class ServerThread extends Thread{
        private TServer server;
        ServerThread(TProcessor processor,int port) throws Exception{
            TNonblockingServerSocket serverSocket=new TNonblockingServerSocket(port);
            TThreadedSelectorServer.Args tArgs=new TThreadedSelectorServer.Args(serverSocket);
            TProcessorFactory processorFactory=new TProcessorFactory(processor);
            tArgs.processorFactory(processorFactory);
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.protocolFactory(new TBinaryProtocol.Factory(true,true));
            server=new TThreadedSelectorServer(tArgs);

        }
        public void run(){
            try{
                //启动服务
                server.serve();
            }catch (Exception e){
                //
                e.printStackTrace();
            }
        }

        public void stopServer(){
            server.stop();
        }


    }
    public void close(){
        serverThread.stopServer();
    }








}
