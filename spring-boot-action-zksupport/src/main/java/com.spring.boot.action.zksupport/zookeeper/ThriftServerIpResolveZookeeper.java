package com.spring.boot.action.zksupport.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by fgm on 2017/5/21.
 */
public class ThriftServerIpResolveZookeeper implements ThriftServerIpResolve {
    private Logger logger= LoggerFactory.getLogger(getClass());
    private String serverIp;


    @Override
    public String getServerIp() throws Exception {
        if(!StringUtils.isEmpty(serverIp)){
            return serverIp;
        }
        Enumeration<NetworkInterface>  netInterfaces=NetworkInterface.getNetworkInterfaces();
        while(netInterfaces.hasMoreElements()){
            NetworkInterface networkInterface=netInterfaces.nextElement();
            Enumeration<InetAddress>  addresses= networkInterface.getInetAddresses();
            while(addresses.hasMoreElements()){
                InetAddress address=addresses.nextElement();
                if(address instanceof Inet6Address){
                    continue;
                }
                if(address.isSiteLocalAddress() && !address.isLoopbackAddress()){
                    serverIp=address.getHostAddress();
                    logger.info("resolve rpc ip:"+serverIp);
                    continue;
                }
            }
        }
        if(StringUtils.isEmpty(serverIp)){
            serverIp="127.0.0.1";
        }
        return serverIp;
    }

    @Override
    public void reset() {
        serverIp=null;
    }
}
