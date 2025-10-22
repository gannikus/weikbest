package com.weikbest.pro.saas.sys.common.delaytaskprocess;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.delay.process.DelayTaskProcess;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/28
 */
@Slf4j
@Component
public class LogoutDelayTaskProcess extends DelayTaskProcess {

    /**
     * KEY前缀
     */
    public static final String TASK_PREFIX = RedisKey.USER_TOKEN_KEY;

    /**
     * 延时队列超时时间：1天
     */
    public static final Long TIME_OUT_1_DAY = TokenUtil.EXPIRE;

    @Resource
    private UserLoginService userLoginService;

    public LogoutDelayTaskProcess() {
        super.setTaskPrefix(TASK_PREFIX);
        super.setTaskLockPrefix(RedisKey.Lock.LOCK_USER_TOKEN);
    }

    /**
     * 将用户登录信息加入延时队列，超时时间：1天
     */
    public void putTask(TokenUser tokenUser) {
        String taskId = TokenUtil.redisUserTokenKey(tokenUser);
        delayQueueManager.putOrUpdateTask(taskId, TIME_OUT_1_DAY);
    }

    /**
     * 将用户登录信息从延时队列删除
     *
     * @param tokenUser 登录用户
     */
    public void removeTask(TokenUser tokenUser) {
        String taskId = TokenUtil.redisUserTokenKey(tokenUser);
        delayQueueManager.removeTask(taskId);
    }

    /**
     * @param taskId 任务ID = USER_TOKEN_KEY:关联用户ID:登录账号:关联用户类型:登录记录ID
     */
    @Override
    public void doProcess(String taskId) {
        // 执行方法
        String[] keySplit = StrUtil.split(taskId, RedisKey.SPLIT);
        Long relateId = Long.valueOf(keySplit[1]);
        String loginName = keySplit[2];
        String userType = keySplit[3];
        Long userLoginRecordId = Long.valueOf(keySplit[4]);
        userLoginService.logoutWithTimeout(relateId, loginName, userType, userLoginRecordId);
    }

    @Override
    public String getName() {
        return "登录过期";
    }
}
