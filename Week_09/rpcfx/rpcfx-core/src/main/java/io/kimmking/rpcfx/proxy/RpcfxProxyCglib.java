package io.kimmking.rpcfx.proxy;

import io.kimmking.rpcfx.filter.Filter;
import org.springframework.cglib.proxy.Enhancer;

public class RpcfxProxyCglib implements RpcfxProxy {
    @Override
    public <T> T create(Class<T> serviceClass, String url, Filter... filters) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new RpcfxInvocationHandler(serviceClass, url));
        enhancer.setSuperclass(serviceClass);
        return (T) enhancer.create();
    }
}
