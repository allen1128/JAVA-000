package io.kimmking.rpcfx.filter;

import io.kimmking.rpcfx.api.RpcfxRequest;

public interface Filter {

    boolean filter(RpcfxRequest request);

    // Filter next();

}
