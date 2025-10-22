package com.weikbest.pro.saas.common.delay.process;

import com.weikbest.pro.saas.common.delay.DelayQueueManager;
import com.weikbest.pro.saas.common.delay.entity.DelayTaskStatus;
import com.weikbest.pro.saas.common.delay.service.DelayQueueServiceFactory;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 延时任务进行程序
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public abstract class DelayTaskProcess {

    @Resource
    protected RedisContext redisContext;
    @Resource
    protected RedisLock redisLock;
    @Resource
    protected DelayQueueManager delayQueueManager;
    /**
     * 监听的延时任务前缀
     */
    private String taskPrefix;
    /**
     * 延时任务锁前缀
     */
    private String taskLockPrefix;

    public DelayTaskProcess() {
    }

    /**
     * 执行方法
     *
     * @param taskId 任务ID
     */
    public void process(String taskId) {
        String lockKey = RedisKey.generalKey(getTaskLockPrefix(), taskId);
        boolean tryLock = redisLock.tryLock(lockKey);
        if (!tryLock) {
            log.info("{}任务执行：{}，taskId：{}，未抢占到执行资源...", this.getName(), this.getClass().getSimpleName(), taskId);
            return;
        }
        String delayTaskStatus = DelayTaskStatus.SUCCESS;
        try {
            log.info("{}任务执行：{}，taskId：{}，开始执行...", this.getName(), this.getClass().getSimpleName(), taskId);
            // 执行方法
            doProcess(taskId);
        } catch (Exception e) {
            log.error(String.format("%s任务执行异常! %s", this.getName(), e.getMessage()), e);
            // TODO 这里如果有问题，到时候可以放到表里，在用一个定时任务去跑没完成的任务
            delayTaskStatus = DelayTaskStatus.ERROR;
        } finally {
            redisLock.unlock(lockKey);
            // 删除redis中的延时队列
            redisContext.hdel(DelayQueueManager.DELAY_QUEUE_HASH_KEY, taskId);
            // 更新其他渠道任务状态
            DelayQueueServiceFactory.updateTaskStatus(taskId, delayTaskStatus);
        }
    }

    /**
     * 真正的执行方法
     *
     * @param taskId 任务ID
     */
    public abstract void doProcess(String taskId);


    public String getTaskPrefix() {
        return taskPrefix;
    }

    public void setTaskPrefix(String taskPrefix) {
        this.taskPrefix = taskPrefix;
    }

    public String getTaskLockPrefix() {
        return taskLockPrefix;
    }

    public void setTaskLockPrefix(String taskLockPrefix) {
        this.taskLockPrefix = taskLockPrefix;
    }

    /**
     * 任务名称
     *
     * @return
     */
    public abstract String getName();

}
