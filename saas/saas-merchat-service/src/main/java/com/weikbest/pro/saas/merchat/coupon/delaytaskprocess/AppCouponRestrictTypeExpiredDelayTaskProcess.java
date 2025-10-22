package com.weikbest.pro.saas.merchat.coupon.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wisdomelon
 * @project saas
 * @jdk 1.8
 * @since 2022/11/12
 * 优惠券领券状态已过期延时任务处理
 */
@Slf4j
@Component
public class AppCouponRestrictTypeExpiredDelayTaskProcess extends DelayTaskProcess {

    /**
     * 优惠券领券状态已过期队列前缀
     */
    public static final String TASK_PREFIX = RedisKey.DelayTask.DELAY_COUPON_RESTRICT_TYPE_EXPIRED_KEY;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;


    public AppCouponRestrictTypeExpiredDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_COUPON_RESTRICT_KEY);
    }

    /**
     * 将优惠券领券状态加入延时队列，超时时间：优惠券用券结束时间
     *
     * @param id      优惠券领券ID
     * @param timeout 优惠券用券结束时间
     */
    public void putTask(Long id, Long timeout) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.putTaskWithTimeout(taskId, timeout);
    }

    /**
     * 将优惠券领券状态从延时队列删除
     *
     * @param id 优惠券ID
     */
    public void removeTask(Long id) {
        String taskId = RedisKey.generalKey(TASK_PREFIX, id);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = RedisKey.DELAY_COUPON_RESTRICT_TYPE_EXPIRED_KEY:优惠券领券ID
     */
    @Override
    public void doProcess(String taskId) {
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        // 获取优惠券信息
        Long id = Long.valueOf(keySplit[1]);
        // 执行优惠券活动开始处理
        custCouponRestrictService.couponRestrictTypeExpiredExecute(id);
    }

    @Override
    public String getName() {
        return "优惠券领券状态已过期延时队列";
    }

}
