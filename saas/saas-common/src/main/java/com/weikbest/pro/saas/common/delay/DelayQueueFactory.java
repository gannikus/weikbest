package com.weikbest.pro.saas.common.delay;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 */
public class DelayQueueFactory {

    /**
     * 匹配对应的延时任务进行程序
     *
     * @param taskId
     * @return
     */
    public static List<DelayTaskProcess> matchDelayTaskProcess(String taskId) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(taskId);

        List<DelayTaskProcess> resultList = new ArrayList<>();

        ApplicationContext applicationContext = SpringContext.getInstance().getApplicationContext();
        Map<String, DelayTaskProcess> beans = applicationContext.getBeansOfType(DelayTaskProcess.class);
        for (Map.Entry<String, DelayTaskProcess> entry : beans.entrySet()) {
            DelayTaskProcess delayTaskProcess = entry.getValue();
            if (redisKeyPrefix.contains(delayTaskProcess.getTaskPrefix())) {
                resultList.add(delayTaskProcess);
            }
        }
        return resultList;
    }

    /**
     * 匹配对应的延时任务进行程序名称
     *
     * @param taskId
     * @return
     */
    public static String matchDelayTaskProcessName(String taskId) {
        String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(taskId);

        ApplicationContext applicationContext = SpringContext.getInstance().getApplicationContext();
        Map<String, DelayTaskProcess> beans = applicationContext.getBeansOfType(DelayTaskProcess.class);
        for (Map.Entry<String, DelayTaskProcess> entry : beans.entrySet()) {
            DelayTaskProcess delayTaskProcess = entry.getValue();
            // 命中一个就返回
            if (redisKeyPrefix.contains(delayTaskProcess.getTaskPrefix())) {
                return delayTaskProcess.getName();
            }
        }
        return "";
    }

    /**
     * 获取任务前缀
     *
     * @param taskId
     * @return
     */
    public static String getRedisKeyPrefix(String taskId) {
        String redisKeyPrefix = taskId.contains(RedisKey.SPLIT) ?
                StrUtil.sub(taskId, 0, taskId.indexOf(RedisKey.SPLIT)) : taskId;
        return redisKeyPrefix;
    }
}
