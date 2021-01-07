package com.camp.redis.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
        testConcurrency();
    }

    public static void testConcurrency() {
        DistributedLockUtil.reset();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                Jedis jedis = DistributedLockUtil.getResource();
                DistributedLockUtil.getLock(jedis);
                try {
                    System.out.println("entering=" + Thread.currentThread().getName());
                } finally {
                    DistributedLockUtil.unlock(jedis);
                    jedis.close();
                    System.out.println("exiting=" + Thread.currentThread().getName());
                }
            }
            );
            t.start();
            list.add(t);
        }

        list.stream()
                .forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
