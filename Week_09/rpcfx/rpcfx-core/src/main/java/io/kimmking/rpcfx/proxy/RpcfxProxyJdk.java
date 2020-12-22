package io.kimmking.rpcfx.proxy;

import io.kimmking.rpcfx.filter.Filter;
import io.kimmking.rpcfx.client.Rpcfx;

import java.lang.reflect.Proxy;

public class RpcfxProxyJdk implements RpcfxProxy {
    @Override
    public <T> T create(Class<T> serviceClass, String url, Filter... filters) {
        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));

    }
}
