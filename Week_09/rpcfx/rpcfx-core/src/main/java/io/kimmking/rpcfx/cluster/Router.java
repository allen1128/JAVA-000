package io.kimmking.rpcfx.cluster;

import java.util.List;

public interface Router {

    List<String> route(List<String> urls);
}
