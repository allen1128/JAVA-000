package com.camp.redis.cluster;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.JedisCluster;

@SpringBootApplication
public class RedisClusterApplication {
    public static void main(String[] args) {
        JedisCluster cluster = ClusterJedis.getJedisCluster();
		for (int i = 0; i < 100; i++) {
			cluster.set("cluster:" + i, "data:" + i);
		}
		System.out.println(cluster.get("cluster:92"));
		ClusterJedis.close();
    }
}
