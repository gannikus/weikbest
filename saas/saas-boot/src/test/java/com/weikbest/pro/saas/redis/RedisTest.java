package com.weikbest.pro.saas.redis;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author wisdomelon
 * @date 2021/5/17
 * @project saas
 * @jdk 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class RedisTest {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisService redisService;


    @Test
    public void tryLock2() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = RedisKey.generalKey(RedisKey.APP_VERIFY_EXPIRE_KEY, "id");
                    RLock lock = redissonClient.getLock(key);
                    if (lock.tryLock()) {
                        System.out.println(Thread.currentThread().getName() + "号线程抢到锁资源");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println(Thread.currentThread().getName() + "号线程finally");
                            // 解锁
                            lock.unlock();
                        }
                    }
                    countDownLatch.countDown();

                }
            }).start();
        }

        countDownLatch.await();
    }

    @Test
    public void lock2() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = RedisKey.generalKey(RedisKey.APP_VERIFY_EXPIRE_KEY, "id");
                    RLock lock = redissonClient.getLock(key);
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "号线程抢到锁资源");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 解锁
                        System.out.println(Thread.currentThread().getName() + "号线程finally");
                        countDownLatch.countDown();
                        lock.unlock();
                    }


                }
            }).start();
        }

        countDownLatch.await();
    }

    @Test
    public void tryLock() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = RedisKey.generalKey(RedisKey.APP_VERIFY_EXPIRE_KEY, "id");
                    boolean lock = redisLock.tryLock(key);
                    if (lock) {
                        System.out.println(Thread.currentThread().getName() + "号线程抢到锁资源");
                        try {
                            Thread.sleep(10000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            // 解锁
                            System.out.println(Thread.currentThread().getName() + "号线程finally");
                            redisLock.unlock(key);
                        }
                    }
                    countDownLatch.countDown();

                }
            }).start();
        }

        countDownLatch.await();
    }

    @Test
    public void lock() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = RedisKey.generalKey(RedisKey.APP_VERIFY_EXPIRE_KEY, "id");
                    String uuid = UUID.randomUUID().toString();
                    redisLock.lock(key);
                    System.out.println(Thread.currentThread().getName() + "号线程抢到锁资源");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 解锁
                        System.out.println(Thread.currentThread().getName() + "号线程finally");
                        countDownLatch.countDown();
                        redisLock.unlock(key);
                    }


                }
            }).start();
        }

        countDownLatch.await();
    }

}
