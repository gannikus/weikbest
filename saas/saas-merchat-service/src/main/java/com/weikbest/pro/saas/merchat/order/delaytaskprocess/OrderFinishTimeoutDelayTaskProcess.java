package com.weikbest.pro.saas.merchat.order.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.sys.param.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/17
 * <p>
 * 订单自动完成延时任务处理
 */
@Slf4j
@Component
public class OrderFinishTimeoutDelayTaskProcess extends DelayTaskProcess {

    /**
     * 订单自动完成延时队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_ORDER_FINISH_TIMEOUT_KEY;

    /**
     * 延时队列超时时间：15天
     */
//    public static final Long TIME_OUT_15_DAY = 15 * 24 * 60 * 60 * 1000L;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private DealService dealService;

    public OrderFinishTimeoutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_ORDER_KEY);
    }

    /**
     * 将订单加入延时队列
     *
     * @param id 订单ID
     */
    public void putTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        // 读取系统交易规则数据
        long timeout = dealService.findDeal().getAutoOrderComplete() * 24 * 60 * 60 * 1000L;
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
     * @param taskId 任务ID = RedisKey.DELAY_ORDER_FINISH_TIMEOUT:订单ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取订单信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行订单确认收货超时处理
        orderInfoService.finishTimeoutExecute(id);
    }

    @Override
    public String getName() {
        return "订单自动完成延时队列";
    }
}
