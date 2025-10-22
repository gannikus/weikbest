package com.weikbest.pro.saas.common.delay.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列任务
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class DelayTask implements Delayed {
    private String taskId;
    private long expire;

    /**
     * 构造延时任务
     *
     * @param taskId 可解析的任务ID，一般是redis的key
     * @param expire 任务延时时间（ms）
     */
    public DelayTask(String taskId, long expire) {
        super();
        this.taskId = taskId;
        this.expire = expire + System.currentTimeMillis();
    }

    /**
     * 构造延时任务,默认10秒
     *
     * @param taskId 可解析的任务ID，一般是redis的key
     */
    public DelayTask(String taskId) {
        super();
        this.taskId = taskId;
        this.expire = (10 * 1000L) + System.currentTimeMillis();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DelayTask) {
            return this.taskId.equals(((DelayTask) obj).getTaskId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" + "taskId:" + taskId + "," + "expire:" + new Date(expire) + "}";
    }

    /**
     * 过期时间 - 系统当前时间  即为该任务的阻塞时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long delayTime = this.expire - System.currentTimeMillis();
//        if(delayTime < 0L) {
//            log.info("taskId: {}, delayTime: {}", this.taskId, delayTime);
//        }
//        log.info("taskId: {}, delayTime: {}", this.taskId, delayTime);
        return unit.convert(delayTime, unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long delta = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
//        log.info("taskId: {}, delayTime: {}, other.taskId: {}, other.delayTime: {}, delta: {}", this.taskId, getDelay(TimeUnit.NANOSECONDS), ((DelayTask) o).getTaskId(), o.getDelay(TimeUnit.NANOSECONDS), delta);
        return Long.compare(delta, 0L);
    }
}
