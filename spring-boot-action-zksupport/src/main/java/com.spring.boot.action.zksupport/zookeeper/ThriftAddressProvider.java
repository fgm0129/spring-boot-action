package com.spring.boot.action.zksupport.zookeeper;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by fgm on 2017/5/21.
 * 服务地址提供者
 *
 */
public interface ThriftAddressProvider {
    /**
     * @description 获取服务名称
     */
    String getService();

    /**
     * @description 获取所有服务地址
     */
    List<InetSocketAddress> findAddressList();

    /**
     * @description 选取一个合适的address,内部可以使用合适算法,
     *              达到负载均衡效果
     */
    InetSocketAddress selector();
    void close();



}
