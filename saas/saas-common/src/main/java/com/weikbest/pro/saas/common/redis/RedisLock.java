package com.weikbest.pro.saas.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wisdomelon
 * @date 2020/7/10
 * @project saas
 * @jdk 1.8
 * redis分布式锁
 */
@Component
@Slf4j
public class RedisLock {

    /**
     * 锁的默认超时时间 300s
     */
    public static final int DEFAULT_TIMEOUT = 300;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁，只有一个线程能够返回true
     * 快速失败
     *
     * @param key
     * @return
     */
    public boolean tryLock(String key) {
        RLock lock = getLock(key);
        return lock.tryLock();
    }

    private RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    /**
     * 加锁，抢占不到则阻塞
     *
     * @param key
     * @return
     */
    public void lock(String key) {
        lock(key, DEFAULT_TIMEOUT);
    }

    /**
     * 加锁，抢占不到则阻塞
     *
     * @param key
     * @param time 多少秒后返回
     * @return
     */
    public void lock(String key, long time) {
        RLock lock = getLock(key);
        lock.lock(time, TimeUnit.SECONDS);
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unlock(String key) {
        RLock lock = getLock(key);
        // lock.isLocked()：判断要解锁的key是否已被锁定。
        // lock.isHeldByCurrentThread()：判断要解锁的key是否被当前线程持有。
        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
            // 释放锁
            lock.unlock();
        }
    }
}
