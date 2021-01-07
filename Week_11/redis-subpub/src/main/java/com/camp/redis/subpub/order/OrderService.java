package com.camp.redis.subpub.order;

import com.camp.redis.subpub.channel.Channel;
import com.camp.redis.subpub.redis.RedisResourceUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class OrderService {
    public Order add(Integer orderId, Integer commodityId, Integer quality) {
        Jedis jedis = RedisResourceUtil.getResource();
        Order o = new Order(orderId, commodityId, quality);
        jedis.publish(Channel.orderChannel, o.toString());
        return o;
    }
}
