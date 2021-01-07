package com.camp.redis.subpub.subscriber;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SubscriberRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Thread t = new SubsriberThread();
        t.start();
    }
}
