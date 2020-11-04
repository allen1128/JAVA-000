package com.java.gateway.router;

import java.util.List;
import java.util.Random;

public class CustomHttpEndpointRouter implements HttpEndpointRouter {
    private List<String> candidateEndpoints;

    public CustomHttpEndpointRouter(List<String> candidateEndpoints) {
        this.candidateEndpoints = candidateEndpoints;
    }

    public String route() {
        int size = candidateEndpoints.size();
        int randomlized = new Random().nextInt(size);
        return candidateEndpoints.get(randomlized);
    }
}
