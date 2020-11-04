package com.java.gateway;

import com.java.gateway.inbound.HttpInboundHandler;
import com.java.gateway.inbound.HttpInboundServer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NettyServerApplication {

    public static void main(String[] args) {
        String urls = System.getProperty("proxyServer","http://localhost:8801,http://localhost:8802");
        List<String> candidates = Arrays.stream(urls.split(",")).map(String::trim).collect(Collectors.toList());
        HttpInboundServer server = new HttpInboundServer(false, 8888, candidates);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
