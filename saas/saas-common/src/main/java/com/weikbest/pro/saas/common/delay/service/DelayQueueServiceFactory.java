package com.weikbest.pro.saas.common.delay.service;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.entity.DelayTask;
import com.weikbest.pro.saas.common.util.context.SpringContext;

import java.util.concurrent.ExecutorService;

/**
 * 延时队列管理器
 *
 * @author wisdomelon
 * @date 2021/6/1
 * @project saas
 * @jdk 1.8
 * 延时队列服务工厂
 */
public class DelayQueueServiceFactory {

    private static final ExecutorService EXECUTOR_SERVICE = ThreadUtil.newExecutor(10);

    /**
     * 排除的beanName
     */
    private static final String EXCLUDE_BEAN_NAME = "defaultDelayQueueService";

    /**
     * 加入到延时队列中，排除默认的，异步操作
     *
     * @param delayTask
     */
    public static void putTask(DelayTask delayTask) {
        EXECUTOR_SERVICE.execute(() -> {
            String[] delayQueueServiceStrArr = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(DelayQueueService.class);
            if (ArrayUtil.isNotEmpty(delayQueueServiceStrArr)) {
                for (String delayQueueServiceBeanName : delayQueueServiceStrArr) {
                    if (!StrUtil.equals(delayQueueServiceBeanName, EXCLUDE_BEAN_NAME)) {
                        DelayQueueService delayQueueService = (DelayQueueService) SpringContext.getInstance().getBean(delayQueueServiceBeanName);
                        delayQueueService.putTask(delayTask);
                    }
                }
            }
        });
    }

    /**
     * 取消延时任务
     *
     * @param delayTask
     * @return
     */
    public static void removeTask(DelayTask delayTask) {
        EXECUTOR_SERVICE.execute(() -> {
            String[] delayQueueServiceStrArr = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(DelayQueueService.class);
            if (ArrayUtil.isNotEmpty(delayQueueServiceStrArr)) {
                for (String delayQueueServiceBeanName : delayQueueServiceStrArr) {
                    if (!StrUtil.equals(delayQueueServiceBeanName, EXCLUDE_BEAN_NAME)) {
                        DelayQueueService delayQueueService = (DelayQueueService) SpringContext.getInstance().getBean(delayQueueServiceBeanName);
                        delayQueueService.removeTask(delayTask);
                    }
                }
            }
        });
    }

    /**
     * 更新延时任务状态
     *
     * @param taskId
     * @param delayTaskStatus
     */
    public static void updateTaskStatus(String taskId, String delayTaskStatus) {
        EXECUTOR_SERVICE.execute(() -> {
            String[] delayQueueServiceStrArr = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(DelayQueueService.class);
            if (ArrayUtil.isNotEmpty(delayQueueServiceStrArr)) {
                for (String delayQueueServiceBeanName : delayQueueServiceStrArr) {
                    if (!StrUtil.equals(delayQueueServiceBeanName, EXCLUDE_BEAN_NAME)) {
                        DelayQueueService delayQueueService = (DelayQueueService) SpringContext.getInstance().getBean(delayQueueServiceBeanName);
                        delayQueueService.updateTaskStatus(taskId, delayTaskStatus);
                    }
                }
            }
        });
    }
}

