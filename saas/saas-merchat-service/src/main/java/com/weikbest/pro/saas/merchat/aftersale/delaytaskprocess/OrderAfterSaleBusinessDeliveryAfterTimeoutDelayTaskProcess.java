package com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.sys.param.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/15
 * <p>
 * 售后单商家确认收货后处理过期延时任务
 */
@Slf4j
@Component
public class OrderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess extends DelayTaskProcess {

    /**
     * 售后单延时队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_ORDER_AFTER_SALE_BUSINESS_DELIVERAFTER_YTIMEOUT_KEY;

    /**
     * 延时队列超时时间：2天
     */
//    public static final Long TIME_OUT_2_DAY = 2 * 24 * 60 * 60 * 1000L;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private DealService dealService;

    public OrderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_AFTER_SALE_KEY);
    }

    /**
     * 将售后单加入延时队列，超时时间：2天
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    public void putTask(Long id, String afterSaleStatus) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id, afterSaleStatus);
        // 读取系统交易规则数据
        long timeout = dealService.findDeal().getRefundBusinessTimeout() * 24 * 60 * 60 * 1000L;
        delayQueueManager.putTask(taskId, timeout);
    }

    /**
     * 将售后单从延时队列删除
     *
     * @param id 售后单ID
     * @param afterSaleStatus 售后单状态
     */
    public void removeTask(Long id, String afterSaleStatus) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id, afterSaleStatus);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = DELAY_ORDER_AFTER_SALE_BUSINESS_DELIVERAFTER_YTIMEOUT_KEY:售后单ID:售后单状态
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取售后单信息
        Long id = Long.valueOf(keySplit[1]);
        String afterSaleStatus = keySplit[2];
        // 执行售后商户确认收货后超时处理
        orderAfterSaleService.businessDeliveryAfterTimeout(id, afterSaleStatus);
    }

    @Override
    public String getName() {
        return "售后单商家确认收货后处理超时延时队列";
    }

}
