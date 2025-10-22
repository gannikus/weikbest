package com.weikbest.pro.saas.common.delay;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.delay.entity.DelayTaskStatus;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.delay.service.DelayQueueServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 延时队列运行程序
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Component
public class DelayQueueRunner implements CommandLineRunner {

    @Resource
    private DelayQueueManager delayQueueManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) {
        log.info("初始化延时队列执行线程...");
        ThreadUtil.newSingleExecutor().execute(this::excuteThread);
    }

    /**
     * 延时任务执行线程
     * 延时队列阻塞监听
     */
    private void excuteThread() {
        log.info("监听延时队列任务....");
        beforeExecuteThreadOutInfo();
        while (true) {
            try {
                DelayTask task = delayQueueManager.getDelayQueue().take();
                if (verifyRedisValue(task.getTaskId() , task.getExpire())){
                    processTask(task);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void beforeExecuteThreadOutInfo() {
        for (DelayTask delayTask : delayQueueManager.getDelayQueue()) {
            log.info("初始化延时任务队列ID：{}， 过期时间点：{} ms, 过期时间：{}", delayTask.getTaskId(), delayTask.getExpire(), DateUtil.date(delayTask.getExpire()).toStringDefaultTimeZone());
        }
    }

    /**
     * 内部执行延时任务
     *
     * @param task
     */
    private void processTask(DelayTask task) {
        try {
            // 拿到taskId
            String taskId = task.getTaskId();
            log.info("监听延时队列任务过期，taskId：{}...", taskId);
            List<DelayTaskProcess> delayTaskProcessList = DelayQueueFactory.matchDelayTaskProcess(taskId);
            boolean match = CollectionUtil.isNotEmpty(delayTaskProcessList);
            if (match) {
                for (DelayTaskProcess delayTaskProcess : delayTaskProcessList) {
                    // 如果taskId匹配成功，则执行指定的callback方法
                    log.info("监听延时队列任务过期，taskId：{}，执行过期调用的类：{}...", taskId, delayTaskProcess.getClass().getSimpleName());
                    delayTaskProcess.process(taskId);
                }
            }
            if (!match) {
                log.info("监听延时队列任务过期，taskId：{}，未找到该taskId对应的过期调用的类...", taskId);
                DelayQueueServiceFactory.updateTaskStatus(taskId, DelayTaskStatus.CANNOT);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 验证本地的过期时间是否和redis的过期时间相等
     * @param taskId: 任务key
     * @param expire: 本地过期时间
     * @return
     */
    public boolean verifyRedisValue(String taskId, long expire){
        log.info("-------- 进入延迟队列验证方法.... , 当前本地任务Id:{} , 过期时间:{}" , taskId , expire);
        HashOperations hash = redisTemplate.opsForHash();
        Long value = (Long) hash.get("DELAY_QUEUE_HASH:", taskId);
        log.info("-------- 延迟队列验证方法_Redis中存放的 key:{} , value:{}" , taskId , value);
        if (ObjectUtils.isEmpty(value)){
            //未查询到redis中的此方法, 不让执行后续逻辑
            return false;
        }
        if (expire != value){
            //redis中的过期时间和本地的过期时间不对等, 重新加载本地的过期时间
            delayQueueManager.comeBackIn(taskId, value);
            return false;
        }
        return true;
    }
}

