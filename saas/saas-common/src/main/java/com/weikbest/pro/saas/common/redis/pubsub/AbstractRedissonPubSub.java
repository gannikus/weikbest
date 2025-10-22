package com.weikbest.pro.saas.common.redis.pubsub;


import org.apache.poi.ss.formula.functions.T;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;


/**
 * 发布订阅抽象类
 */
public abstract class AbstractRedissonPubSub {

    private final RTopic topic;

    public AbstractRedissonPubSub(String pubSubChannelName, RedissonClient redissonClient) {
        this.topic = redissonClient.getTopic(pubSubChannelName);
    }

    protected void publishMessage(T message) {
        topic.publish(message);
    }

    protected void subscribe() {
        try {
            topic.addListener(T.class, (charSequence, o) -> handleMessage(o));
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
    /**
     * 具体处理消息方法
     */
    protected abstract void handleMessage(T message);

    protected void unsubscribe() {
        if (topic.countListeners() > 0) {
            topic.removeAllListeners();
        }
    }

}

