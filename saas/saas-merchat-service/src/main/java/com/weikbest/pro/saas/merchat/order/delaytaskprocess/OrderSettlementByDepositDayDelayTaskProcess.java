package com.weikbest.pro.saas.merchat.order.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.sys.param.service.SettleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lp
 * @project saas
 * @jdk 1.8
 * @since :2022/12/20
 * <p>
 * 订单分账押金回退清算延时任务处理
 */
@Slf4j
@Component
public class OrderSettlementByDepositDayDelayTaskProcess extends DelayTaskProcess {

    /**
     * 订单资金解冻超过小时数时解冻队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.ORDER_SETTLEMENT_REBATE_OF_DEPOSIT_DAY_TIMEOUT_KEY;


    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private SettleService settleService;


    public OrderSettlementByDepositDayDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_KEY);
    }

    /**
     * 将订单加入延时队列，超时时间：7天
     *
     * @param id 订单ID
     */
    public void putTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        // 读取系统结算规则数据
        // todo:
        long timeout = settleService.findSettle().getRebateOfDepositDay() * 24 * 60 * 60 * 1000L;
//        long timeout = 10 * 60 * 1000L;
        delayQueueManager.putTask(taskId, timeout);
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
     * @param taskId 任务ID = RedisKey.ORDER_SETTLEMENT_REBATE_OF_DEPOSIT_DAY_TIMEOUT:订单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取订单信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行分账押金回退清算
        orderInfoService.doOrderSettlement(id);
    }

    @Override
    public String getName() {
        return "订单分账押金回退清算延时任务";
    }

}
