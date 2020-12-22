package io.kimmking.rpcfx.proxy;

import io.kimmking.rpcfx.filter.Filter;

public interface RpcfxProxy {
    <T> T create(final Class<T> serviceClass, final String url, Filter... filters);
}
