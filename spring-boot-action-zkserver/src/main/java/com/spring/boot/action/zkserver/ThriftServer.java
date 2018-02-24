package com.spring.boot.action.zkserver;

import com.spring.boot.action.api.HelloWordService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by fgm on 2017/5/19.
 */
public class ThriftServer {


    public static void main(String[] args) throws IOException, TTransportException {

        ServerSocket socket = new ServerSocket(7912);
        TServerSocket serverTransport = new TServerSocket(socket);
        HelloWordService.Processor processor = new HelloWordService.Processor(new HelloWorldServiceImpl());

        TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

        // Use this for a multithreaded server
        // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

        System.out.println("Running server...");
        server.serve();

    }

}
