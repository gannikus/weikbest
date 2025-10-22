package com.weikbest.pro.saas.common.redis.listener;

import org.springframework.data.redis.connection.Message;

/**
 * redis监听key过期后的回调
 *
 * @author wisdomelon
 * @date 2021/5/27
 * @project saas
 * @jdk 1.8
 */
public abstract class RedisKeyExpireCallback {

    /**
     * 监听的redisKey前缀
     */
    private String redisKeyPrefix;

    public RedisKeyExpireCallback() {
    }

    public RedisKeyExpireCallback(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
    }

    /**
     * 回调方法
     *
     * @param message
     * @param pattern
     */
    public abstract void callback(Message message, byte[] pattern);

    public String getRedisKeyPrefix() {
        return redisKeyPrefix;
    }

    public void setRedisKeyPrefix(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
    }
}
