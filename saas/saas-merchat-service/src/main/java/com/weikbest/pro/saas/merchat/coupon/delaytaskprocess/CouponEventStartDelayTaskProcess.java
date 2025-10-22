package com.weikbest.pro.saas.merchat.coupon.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wisdomelon
 * @project saas
 * @jdk 1.8
 * @since 2022/11/12
 * 优惠券活动生效延时任务处理
 */
@Slf4j
@Component
public class CouponEventStartDelayTaskProcess extends DelayTaskProcess {

    /**
     * 优惠券活动生效队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_COUPON_EVENT_START_KEY;

    @Resource
    private CouponService couponService;

    public CouponEventStartDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_COUPON_KEY);
    }

    /**
     * 将优惠券加入延时队列，超时时间：优惠券活动开始时间
     *
     * @param id      优惠券ID
     * @param timeout 优惠券活动开始时间
     */
    public void putTask(Long id, Long timeout) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.putTaskWithTimeout(taskId, timeout);
    }

    /**
     * 将优惠券从延时队列删除
     *
     * @param id 优惠券ID
     */
    public void removeTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = RedisKey.DELAY_COUPON_EVENT_START:优惠券ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取优惠券信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行优惠券活动开始处理
        couponService.couponEventStartExecute(id);
    }

    @Override
    public String getName() {
        return "优惠券活动生效延时队列";
    }

}
