package io.kimmking.rpcfx.cluster;

import java.util.List;

public interface LoadBalancer {

    String select(List<String> urls);

}
