package com.weikbest.pro.saas.merchat.complaint.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/24
 * 投诉单商家处理过期延时任务
 */
@Slf4j
@Component
public class OrderComplaintBusinessExecuteTimeoutDelayTaskProcess extends DelayTaskProcess {

    /**
     * 投诉单延时队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.ORDER_COMPLAINT_BUSINESS_EXECUTE_TIMEOUT_KEY;

    /**
     * 延时队列超时时间：3天
     */
    public static final Long TIME_OUT_3_DAY = 3 * 24 * 60 * 60 * 1000L;

    @Resource
    private OrderComplaintService orderComplaintService;

    public OrderComplaintBusinessExecuteTimeoutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_COMPLAINT_KEY);
    }

    /**
     * 将投诉单加入延时队列，超时时间：3天
     *
     * @param id 投诉单ID
     */
    public void putTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.putTask(taskId, TIME_OUT_3_DAY);
    }

    /**
     * 将投诉单从延时队列删除
     *
     * @param id 投诉单ID
     */
    public void removeTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = ORDER_COMPLAINT_BUSINESS_EXECUTE_TIMEOUT:投诉单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取投诉单信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行投诉商户确认收货后超时处理
        orderComplaintService.businessExecuteTimeout(id);
    }

    @Override
    public String getName() {
        return "投诉单商家处理过期延时队列";
    }
}
