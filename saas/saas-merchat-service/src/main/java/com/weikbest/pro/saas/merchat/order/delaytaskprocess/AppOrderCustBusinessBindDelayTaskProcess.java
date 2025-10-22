package com.weikbest.pro.saas.merchat.order.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wisdomelon
 * @project saas
 * @jdk 1.8
 * @since 2022/11/12
 * 客户绑定商户有效期到期延时任务处理
 */
@Slf4j
@Component
public class AppOrderCustBusinessBindDelayTaskProcess extends DelayTaskProcess {

    /**
     * 客户绑定商户有效期到期队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.ORDER_CUST_BUSINESS_BIND_KEY;

    @Resource
    private OrderInfoService orderInfoService;


    public AppOrderCustBusinessBindDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_CUST_BUSINESS_BIND_KEY);
    }

    /**
     * 将客户绑定商户加入延时队列，超时时间：有效期+绑定日期
     *
     * @param id      客户商家绑定表ID
     * @param timeout 有效期时间
     */
    public void putTask(Long id, Long timeout) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.putTaskWithTimeout(taskId, timeout);
    }

    /**
     * 将客户绑定商户从延时队列删除
     *
     * @param id 客户商家绑定表ID
     */
    public void removeTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = RedisKey.DelayTask.ORDER_CUST_BUSINESS_BIND_KEY:客户商家绑定表ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取客户绑定商户信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行客户解除绑定商户开始处理
        orderInfoService.taskUnBind(id);
    }

    @Override
    public String getName() {
        return "客户绑定商户延时队列";
    }

}
