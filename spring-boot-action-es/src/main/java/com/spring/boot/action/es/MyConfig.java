package com.spring.boot.action.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fgm on 2017/9/30.
 */
//@Configuration
//public class MyConfig {
//
//    @Bean
//    public Client client(){
//        TransportClient client = new TransportClient();
//        TransportAddress address = new InetSocketTransportAddress("127.0.0.1",9300);
//        client.addTransportAddress(address);
//        return client;
//    }
//
//
//}
