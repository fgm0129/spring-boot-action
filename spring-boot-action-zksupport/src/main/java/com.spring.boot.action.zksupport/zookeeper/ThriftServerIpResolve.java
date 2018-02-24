package com.spring.boot.action.zksupport.zookeeper;

/**
 * Created by fgm on 2017/5/21.
 * 服务器地址获取
 *  解析thrift-server端IP地址，用于注册服务
 * 1) 可以从一个物理机器或者虚机的特殊文件中解析
 *
 */
public interface ThriftServerIpResolve {

    String getServerIp() throws Exception;

    void reset();


}
