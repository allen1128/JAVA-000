package com.java.gateway.util;

public class ProxyServerUtil {
    public static String getProxyServers() {
        return System.getProperty("proxyServer","http://localhost:8801");
    }
}
