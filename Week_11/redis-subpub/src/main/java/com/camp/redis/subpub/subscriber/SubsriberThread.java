package com.camp.redis.subpub.subscriber;

import com.camp.redis.subpub.channel.Channel;
import com.camp.redis.subpub.redis.RedisResourceUtil;
import redis.clients.jedis.Jedis;

public class SubsriberThread extends Thread {
    @Override
    public void run () {
        Jedis jedis = RedisResourceUtil.getResource();
        try {
            jedis.subscribe(new Subscriber(), Channel.orderChannel);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
