package com.camp.redis.subpub.subscriber;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {

    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribed"));
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribed"));
    }

    public void onPong(String pattern) {
        System.out.println(String.format("pong"));
    }

}
