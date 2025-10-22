package com.weikbest.pro.saas.common.delay.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.weikbest.pro.saas.common.delay.DelayQueueFactory;
import com.weikbest.pro.saas.common.delay.DelayTaskVO;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.redis.RedisContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.DelayQueue;

import static com.weikbest.pro.saas.common.delay.DelayQueueManager.DELAY_QUEUE_HASH_KEY;

/**
 * 延时队列管理器
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 * 延时队列服务
 */
@Slf4j
@Component("defaultDelayQueueService")
public class DefaultDelayQueueServiceImpl implements DelayQueueService {

    /**
     * 延时队列
     */
    private final DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
    @Resource
    private RedisContext redisContext;

    /**
     * 加入到延时队列中
     *
     * @param delayTask
     */
    @Override
    public void putTask(DelayTask delayTask) {
        delayQueue.put(delayTask);
        log.info("加入延时任务ID：{}， 过期时间点：{} ms, 过期时间：{}, 队列当前任务数：{}", delayTask.getTaskId(), delayTask.getExpire(), DateUtil.date(delayTask.getExpire()).toStringDefaultTimeZone(), delayQueue.size());
        // 同时将任务加入到redis中
        redisContext.hset(DELAY_QUEUE_HASH_KEY, delayTask.getTaskId(), delayTask.getExpire());
    }

    @Override
    public void putTaskInit(DelayTask delayTask) {
        delayQueue.put(delayTask);
        log.info("加入延时任务ID：{}， 过期时间点：{} ms, 过期时间：{}, 队列当前任务数：{}", delayTask.getTaskId(), delayTask.getExpire(), DateUtil.date(delayTask.getExpire()).toStringDefaultTimeZone(), delayQueue.size());
    }

    /**
     * 取消延时任务
     *
     * @param delayTask@return
     */
    @Override
    public boolean removeTask(DelayTask delayTask) {
        boolean remove = delayQueue.remove(delayTask);
        log.info("删除延时任务ID：{}，本地队列是否有次任务:{}, 队列当前任务数：{} ", delayTask.getTaskId(), remove, delayQueue.size());
        // 同时将任务从redis中删除
        redisContext.hdel(DELAY_QUEUE_HASH_KEY, delayTask.getTaskId());
        return remove;
    }

    @Override
    public boolean contains(DelayTask delayTask) {
        return delayQueue.contains(delayTask);
    }

    /**
     * 获取所有的在队列中的延时任务
     *
     * @return
     */
    public List<DelayTaskVO> getDelayQueueTask() {
        List<DelayTaskVO> resultList = new ArrayList<>();

        Map<Object, Object> hmget = redisContext.hmget(DELAY_QUEUE_HASH_KEY);
        if (CollectionUtil.isNotEmpty(hmget)) {
            for (Map.Entry<Object, Object> entry : hmget.entrySet()) {
                String taskId = (String) entry.getKey();
                String delayTaskProcessName = DelayQueueFactory.matchDelayTaskProcessName(taskId);

                // 这里取出来的时间是加过毫秒数的大long
                long expire = (Long) entry.getValue();
                DateTime date = DateUtil.date(expire);

                DelayTaskVO delayTaskVO = new DelayTaskVO(taskId, delayTaskProcessName, date);
                resultList.add(delayTaskVO);

            }
        }

        return resultList;
    }


    public DelayQueue<DelayTask> getDelayQueue() {
        return delayQueue;
    }
}

