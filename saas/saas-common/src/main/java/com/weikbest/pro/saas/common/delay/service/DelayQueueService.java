package com.weikbest.pro.saas.common.delay.service;

import com.weikbest.pro.saas.common.delay.entity.DelayTask;

/**
 * 延时队列管理器
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 * 延时队列服务
 */
public interface DelayQueueService {

    /**
     * 加入到延时队列中
     *
     * @param delayTask
     */
    void putTask(DelayTask delayTask);

    /**
     * 加入到延时队列中
     *
     * @param delayTask
     */
    void putTaskInit(DelayTask delayTask);
    /**
     * 取消延时任务
     *
     * @param delayTask
     * @return
     */
    boolean removeTask(DelayTask delayTask);

    /**
     * 延时任务是否存在
     *
     * @param delayTask
     * @return
     */
    boolean contains(DelayTask delayTask);


    /**
     * 更新任务状态
     *
     * @param taskId
     * @param delayTaskStatus
     */
    default void updateTaskStatus(String taskId, String delayTaskStatus) {
    }
}

