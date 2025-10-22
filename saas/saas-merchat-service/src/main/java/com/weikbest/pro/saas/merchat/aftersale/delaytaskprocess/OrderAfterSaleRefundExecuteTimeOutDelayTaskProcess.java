package com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.sys.param.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 售后单立即退款延迟队列任务
 *
 * @author 甘之萌  2023/05/17 17:47
 */
@Slf4j
@Component
public class OrderAfterSaleRefundExecuteTimeOutDelayTaskProcess extends DelayTaskProcess {


    /**
     * 售后单延时队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_ORDER_AFTER_SALE_REFUND_KEY;

    /**
     * 延时队列超时时间：2小时
     */
//    public static final Long TIME_OUT_7_DAY = 7 * 24 * 60 * 60 * 1000L;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private DealService dealService;

    @Resource
    private OrderPayRecordService orderPayRecordService;

    public OrderAfterSaleRefundExecuteTimeOutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_AFTER_SALE_KEY);
    }

    /**
     * 将售后单退款任务加入延时队列，超时时间：2小时
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    public void putTask(Long id, String afterSaleStatus) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id, afterSaleStatus);

        // 读取系统交易规则数据
        long timeout = dealService.findDeal().getFastRefundCondition2() * 60 * 60 * 1000L;
//        long timeout =  2 * 60 * 1000L;
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
     * @param taskId 任务ID = DELAY_ORDER_AFTER_SALE_REFUND_KEY:售后单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取售后单信息
        Long id = Long.valueOf(keySplit[1]);

        // 执行售后客户发货超时处理
        orderAfterSaleService.refundDelayTimeOut(id);
    }

    @Override
    public String getName() {
        return "售后单立即退款任务超时延时队列";
    }
}
