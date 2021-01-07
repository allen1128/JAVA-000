package com.camp.redis.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisCounterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCounterApplication.class, args);
        testRedisCounter();
    }

    public static void testRedisCounter() {
        DistributedLockUtil.reset();

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = DistributedLockUtil.getResource();
                    DistributedLockUtil.getLock(jedis);
                    try {
                        Integer c = 0;
                        String val = jedis.get("counter");
                        System.out.println("before=" + val);
                        if (val != null) {
                            c = Integer.valueOf(val);
                        };
                        c++;
                        jedis.set("counter", c.toString());
                        System.out.println("after=" + jedis.get("counter"));
                    } finally {
                        DistributedLockUtil.unlock(jedis);
                        jedis.close();
                    }
                }
            });
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

        System.out.println(DistributedLockUtil.getResource().get("counter"));
    }

}
