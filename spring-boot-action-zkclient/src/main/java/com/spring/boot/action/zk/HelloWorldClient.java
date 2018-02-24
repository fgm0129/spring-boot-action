package com.spring.boot.action.zk;

import com.spring.boot.action.api.HelloWordService;
import com.spring.boot.action.api.Request;
import com.spring.boot.action.api.RequestType;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by fgm on 2017/5/21.
 */
public class HelloWorldClient {


    public static void main(String[] args) throws  Exception {

        TTransport transport=new TSocket("localhost",7912);
        TProtocol protocol=new TBinaryProtocol(transport);
        HelloWordService.Client client=new HelloWordService.Client(protocol);
        transport.open();
        Request request=new Request();
        request.setType(RequestType.SAY_HELLO).setName("fangguangming");
        System.out.println(client.doAction(request));



        Request request2=new Request();
        request2.setType(RequestType.QUERY_TIME).setName("fangguangming");
        System.out.println(client.doAction(request2));
        transport.close();


    }
}
