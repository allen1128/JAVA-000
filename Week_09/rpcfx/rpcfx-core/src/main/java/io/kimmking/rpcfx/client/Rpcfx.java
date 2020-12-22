package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.cluster.LoadBalancer;
import io.kimmking.rpcfx.cluster.Router;
import io.kimmking.rpcfx.filter.Filter;
import io.kimmking.rpcfx.proxy.RpcfxProxyCglib;
import io.kimmking.rpcfx.proxy.RpcfxProxyJdk;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Rpcfx {

    private static CuratorFramework client;

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .namespace("rpcfx")
                .retryPolicy(retryPolicy)
                .build();
        client.start();
    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一
        String orderService = "io.kimking.rpcfx.demo.api.OrderService";
        List<String> invokers = new ArrayList<>();
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        try {
            invokers = client.getChildren()
                    .forPath("/" + orderService)
                    .stream()
                    .map(str -> str.replace("_", ":"))
                    .map(str -> "http://" + str)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        System.out.println("picked url=" + url);
        return (T) create(serviceClass, url, filter);
    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {
        return new RpcfxProxyCglib().create(serviceClass, url, filters);
    }
}
