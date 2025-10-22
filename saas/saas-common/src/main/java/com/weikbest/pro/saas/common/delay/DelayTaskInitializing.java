package com.weikbest.pro.saas.common.delay;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.delay.entity.DelayTaskStatus;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.delay.service.DelayQueueServiceFactory;
import com.weikbest.pro.saas.common.redis.RedisContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 实现spring后置处理器，从redis中抓取任务添加到延时队列中，应对系统重启的情况
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@EnableAsync
@Component
public class DelayTaskInitializing implements ApplicationRunner {

    @Resource
    private RedisContext redisContext;

    @Resource
    private DelayQueueManager delayQueueManager;



//    ExecutorService pool = Executors.newFixedThreadPool(10);

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        log.info("延迟队列初始化开始.............");
//        String delayRedisKey = DelayQueueManager.DELAY_QUEUE_HASH_KEY;
//        Map<Object, Object> hmget = redisContext.hmget(delayRedisKey);
//        if (CollectionUtil.isNotEmpty(hmget)) {
//            for (Map.Entry<Object, Object> entry : hmget.entrySet()) {
//                String taskId = (String) entry.getKey();
//                // 这里取出来的时间是加过毫秒数的大long，因此使用putTaskWithTimeout方法处理
//                long expire = (Long) entry.getValue();
//                log.info("延迟队列初始化，从redis中获取到的taskId：{}， 超时时间点： {}ms，超时时间：{}", taskId, expire, DateUtil.date(expire).toStringDefaultTimeZone());
//                delayQueueManager.putTaskWithTimeout(taskId, expire);
//            }
//        }
//        log.info("延迟队列初始化结束.............");
//
//    }

//    @Override
//    @Async
//    public void run(ApplicationArguments args) throws Exception {
//        log.info("延迟队列初始化开始.............");
//        String delayRedisKey = DelayQueueManager.DELAY_QUEUE_HASH_KEY;
//        Map<Object, Object> hmget = redisContext.hmget(delayRedisKey);
//        if (CollectionUtil.isNotEmpty(hmget)) {
//            for (Map.Entry<Object, Object> entry : hmget.entrySet()) {
//                String taskId = (String) entry.getKey();
//                // 这里取出来的时间是加过毫秒数的大long，因此使用putTaskWithTimeout方法处理
//                long expire = (Long) entry.getValue();
//                log.info("延迟队列初始化，从redis中获取到的taskId：{}， 超时时间点： {}ms，超时时间：{}", taskId, expire, DateUtil.date(expire).toStringDefaultTimeZone());
//                delayQueueManager.putTaskWithTimeout(taskId, expire);
//            }
//        }
//        log.info("延迟队列初始化结束.............");
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        ExecutorService executorService = ThreadUtil.newSingleExecutor();
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 非阻塞线程池
        ExecutorService executorService = ExecutorBuilder.create()
                .setCorePoolSize(2)
                .setMaxPoolSize(4)
                .setWorkQueue(new LinkedBlockingQueue<>())
                .build();
        log.info("延迟队列初始化开始.............");
        String delayRedisKey = DelayQueueManager.DELAY_QUEUE_HASH_KEY;
        Map<Object, Object> hmget = redisContext.hmget(delayRedisKey);
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(hmget);
        if (CollectionUtil.isNotEmpty(concurrentHashMap)) {
            for (ConcurrentHashMap.Entry<Object, Object> entry : concurrentHashMap.entrySet()) {

                // 重写runnable对象中的run方法
                Runnable runnable = () -> {
                    // 业务逻辑
                    String taskId = (String) entry.getKey();
                    // 这里取出来的时间是加过毫秒数的大long，因此使用putTaskWithTimeout方法处理
                    long expire = (Long) entry.getValue();
                    log.info("延迟队列初始化，从redis中获取到的taskId：{}， 超时时间点： {}ms，超时时间：{}", taskId, expire, DateUtil.date(expire).toStringDefaultTimeZone());
                    delayQueueManager.putTaskWithTimeoutInit(taskId, expire);
                };
                executorService.execute(runnable);

            }
        }
        executorService.shutdown();
        log.info("延迟队列初始化结束.............");
    }
}
