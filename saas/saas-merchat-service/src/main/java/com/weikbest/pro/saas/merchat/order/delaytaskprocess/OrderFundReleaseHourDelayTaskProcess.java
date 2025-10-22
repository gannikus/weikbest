package com.weikbest.pro.saas.merchat.order.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.sys.param.service.DealService;
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
 * 订单资金解冻超过小时数时解冻超时延时任务处理
 */
@Slf4j
@Component
public class OrderFundReleaseHourDelayTaskProcess extends DelayTaskProcess {

    /**
     * 订单资金解冻超过小时数时解冻队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.ORDER_FUND_RELEASE_HOUR_TIMEOUT_KEY;

    /**
     * 延时队列超时时间：24小时
     */
//    public static final Long TIME_OUT_1_DAY = 1 * 24 * 60 * 60 * 1000L;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private SettleService settleService;


    public OrderFundReleaseHourDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_KEY);
    }

    /**
     * 将订单加入延时队列，超时时间：24小时
     *
     * @param id 订单ID
     */
    public void putTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        // 读取系统结算规则数据
        long timeout = settleService.findSettle().getOrderFundReleaseHour() * 60 * 60 * 1000L;
        // todo:
//        long timeout =  5 * 60 * 1000L;
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
     * @param taskId 任务ID = RedisKey.ORDER_FUND_RELEASE_HOUR_TIMEOUT:订单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取订单信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行解冻资金处理
        orderInfoService.orderFundReleaseHourTimeout(id);
    }

    @Override
    public String getName() {
        return "订单资金解冻超过小时数时解冻延时任务";
    }

}
