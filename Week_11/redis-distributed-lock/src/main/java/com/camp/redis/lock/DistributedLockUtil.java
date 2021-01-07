package com.camp.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class DistributedLockUtil {

    public static JedisPool POOL = buildJedisPool();

    private static JedisPool buildJedisPool() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(110);
        poolConfig.setMaxIdle(16);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60)
                .toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30)
                .toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        JedisPool pool = new JedisPool(poolConfig, "localhost", 6379);
        return pool;
    }

    public static Jedis getResource() {
        return POOL.getResource();
    }

    public static void reset() {
        Jedis jedis = POOL.getResource();
        jedis.del("counter");
        unlock(jedis);
        jedis.close();
    }

    public static void unlock(Jedis jedis) {
        //System.out.println("thread=" + Thread.currentThread().getName() + " existing");
        jedis.del("lock");
    }

    public static void getLock(Jedis jedis) {
        while (true) {
            Long o = jedis.setnx("lock", "1");
            if (o == 1) {
                //System.out.println("thread=" + Thread.currentThread().getName() + " entering");
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
