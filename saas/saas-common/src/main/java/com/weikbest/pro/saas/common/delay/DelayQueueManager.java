package com.weikbest.pro.saas.common.delay;

import cn.hutool.core.date.DateTime;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.delay.service.DefaultDelayQueueServiceImpl;
import com.weikbest.pro.saas.common.delay.service.DelayQueueServiceFactory;
import com.weikbest.pro.saas.common.redis.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * 延时队列管理器
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Component
public class DelayQueueManager {

    public static final String DELAY_QUEUE_HASH_KEY = RedisKey.generalKey(RedisKey.DELAY_QUEUE_HASH_KEY);

    @Resource
    private DefaultDelayQueueServiceImpl defaultDelayQueueService;

    /**
     * 加入到延时队列中
     *
     * @param taskId
     */
    public void putTask(String taskId) {
        DelayTask delayTask = new DelayTask(taskId);
        if (defaultDelayQueueService.contains(delayTask)) {
            log.info("延时任务ID：{}，在队列中已存在，不在重复添加 ", taskId);
            return;
        }
        // 默认保存任务
        defaultDelayQueueService.putTask(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }

    /**
     * 加入到延时队列中
     *
     * @param taskId
     * @param expire 任务过期事件 一般是指该任务多少ms后过期， 小Long
     */
    public void putTask(String taskId, long expire) {
        DelayTask delayTask = new DelayTask(taskId, expire);
        if (defaultDelayQueueService.contains(delayTask)) {
            log.info("延时任务ID：{}，在队列中已存在，不在重复添加 ", taskId);
            return;
        }
        // 默认保存任务
        defaultDelayQueueService.putTask(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }


    /**
     * 加入到延时队列中，如果存在就更新
     *
     * @param taskId
     * @param expire 任务过期事件 一般是指该任务多少ms后过期， 小Long
     */
    public void putOrUpdateTask(String taskId, long expire) {
        DelayTask delayTask = new DelayTask(taskId, expire);
        // 默认保存任务
        defaultDelayQueueService.putTask(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }

    /**
     * 加入到延时队列中
     *
     * @param taskId
     * @param expire 任务超时时间点 一般是已经计算好之后的时间  大Long型
     */
    public void putTaskWithTimeout(String taskId, long expire) {
        DelayTask delayTask = new DelayTask(taskId);
        delayTask.setExpire(expire);
        if (defaultDelayQueueService.contains(delayTask)) {
            log.info("延时任务ID：{}，在队列中已存在，不在重复添加 ", taskId);
            return;
        }
        // 默认保存任务
        defaultDelayQueueService.putTask(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }
    /**
     * 加入到延时队列中
     *
     * @param taskId
     * @param expire 任务超时时间点 一般是已经计算好之后的时间  大Long型
     */
    public void putTaskWithTimeoutInit(String taskId, long expire) {
        DelayTask delayTask = new DelayTask(taskId);
        delayTask.setExpire(expire);
        if (defaultDelayQueueService.contains(delayTask)) {
            log.info("延时任务ID：{}，在队列中已存在，不在重复添加 ", taskId);
            return;
        }
        // 默认保存任务
        defaultDelayQueueService.putTaskInit(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }
    /**
     * 加入到延时队列中
     *
     * @param taskId
     * @param expireDateTime 任务超时时间点 一般是已经计算好之后的时间
     */
    public void putTaskWithTimeout(String taskId, DateTime expireDateTime) {
        DelayTask delayTask = new DelayTask(taskId);
        delayTask.setExpire(expireDateTime.getTime());
        if (defaultDelayQueueService.contains(delayTask)) {
            log.info("延时任务ID：{}， 剩余过期超时时间：{}, 在队列中已存在，不在重复添加 ", taskId, expireDateTime.toStringDefaultTimeZone());
            return;
        }
        // 默认保存任务
        defaultDelayQueueService.putTask(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }


    /**
     * 重新加入本地延迟队列,已经计算好时间
     * @param taskId
     * @param expire 任务超时时间点
     */
    public void comeBackIn(String taskId, long expire) {
        DelayTask delayTask = new DelayTask(taskId);
        delayTask.setExpire(expire);

        DelayQueue<DelayTask> delayQueue = getDelayQueue();
        delayQueue.put(delayTask);
        // 其他保存任务
        DelayQueueServiceFactory.putTask(delayTask);
    }


    /**
     * 取消延时任务
     *
     * @param taskId
     * @return
     */
    public boolean removeTask(String taskId) {
        DelayTask delayTask = new DelayTask(taskId, 0);
        // 默认删除任务
        boolean remove = defaultDelayQueueService.removeTask(delayTask);
        // 其他删除任务
        DelayQueueServiceFactory.removeTask(delayTask);
        return remove;
    }


    /**
     * 获取所有的在队列中的延时任务
     *
     * @return
     */
    public List<DelayTaskVO> getDelayQueueTask() {
        return defaultDelayQueueService.getDelayQueueTask();
    }

    /**
     * 获取延时任务队列
     *
     * @return
     */
    public DelayQueue<DelayTask> getDelayQueue() {
        return defaultDelayQueueService.getDelayQueue();
    }
}

