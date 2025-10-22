package com.weikbest.pro.saas.merchat.order.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/17
 * <p>
 * 订单商家发货超时延时任务处理
 */
@Slf4j
@Component
public class OrderDeliverTimeoutDelayTaskProcess extends DelayTaskProcess {

    /**
     * 订单发货超时延时队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_ORDER_DELIVER_TIMEOUT_KEY;

    /**
     * 延时队列超时时间：1天
     */
    public static final Long TIME_OUT_1_DAY = 24 * 60 * 60 * 1000L;

    @Resource
    private OrderInfoService orderInfoService;

    public OrderDeliverTimeoutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_KEY);
    }

    /**
     * 将订单加入延时队列，超时时间：1天
     *
     * @param id 订单ID
     */
    public void putTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.putTask(taskId, TIME_OUT_1_DAY);
    }

    /**
     * 将订单从延时队列删除
     *
     * @param id 订单ID
     */
    public void removeTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = RedisKey.DELAY_ORDER_DELIVER_TIMEOUT:订单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取订单信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行订单发货超时处理
        orderInfoService.deliverTimeoutExecute(id);
    }

    @Override
    public String getName() {
        return "订单商家发货超时延时队列";
    }

}
