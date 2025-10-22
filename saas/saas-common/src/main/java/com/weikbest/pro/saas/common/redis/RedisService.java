package com.weikbest.pro.saas.common.redis;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 提供一些操作redis中数据的公共方法
 *
 * @author wisdomelon
 * @date 2021/5/21
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Component
public class RedisService {

    /**
     * 抖音/微信的token名称
     */
    public static final String ACCESS_TOKEN_KEY = "access_token";
    /**
     * 第三方配置名称
     */
    public static final String THIRD_CONFIG_EXPIRE_KEY = "third_config";
    /**
     * 验证码超时市场
     */
    private static final Long VERIFY_EXPIRE_TIME = 5 * 60L;
    /**
     * 抖音/微信的access_token在redis存储的默认超时时长
     */
    private static final Long ACCESS_EXPIRE_TIME = 30 * 60L;
    @Autowired
    private RedisContext redisContext;

    @Autowired
    private RedisLock redisLock;


    /**
     * 获取验证码
     *
     * @param redisKey
     * @param nums        验证码位数
     * @param refreshFlag 是否每次刷新 false 定时刷新  true 每次刷新
     * @param hasLetra    是否包含字母 true：生成的验证码包含字母  false：生成的验证码仅包括数字
     * @return
     */
    public String generalVerify(String redisKey, int nums, boolean refreshFlag, boolean hasLetra) {
        // 1. 从redisContext 取出验证码
        String redisVerify = (String) redisContext.get(redisKey);

        // 2. 判断验证码是否每次刷新 或者 是否失效  如满足任一条件则需要重新生成新的验证码
        if (refreshFlag || StrUtil.isBlank(redisVerify)) {

            // 3. 生成新的验证码
            // 生成4位随机值
            redisVerify = hasLetra ? RandomUtil.randomString(nums) : RandomUtil.randomNumbers(nums);

            // 放入redis中，5分钟失效
            redisContext.set(redisKey, redisVerify, 5 * 60);
        }
        return redisVerify;
    }

    /**
     * 获取验证码， 默认生成4位
     *
     * @param redisKey
     * @param refreshFlag 是否每次刷新 false 定时刷新  true 每次刷新
     * @param hasLetra    是否包含字母 true：生成的验证码包含字母  false：生成的验证码仅包括数字
     * @return
     */
    public String generalVerify(String redisKey, boolean refreshFlag, boolean hasLetra) {
        return generalVerify(redisKey, 4, refreshFlag, hasLetra);
    }

    /**
     * 获取验证码， 默认生成4位，包含字母
     *
     * @param redisKey
     * @param refreshFlag 是否每次刷新 false 定时刷新  true 每次刷新
     * @return
     */
    public String generalLetraVerify(String redisKey, boolean refreshFlag) {
        return generalVerify(redisKey, refreshFlag, true);
    }

    /**
     * 获取验证码， 默认生成4位，仅包含数字
     *
     * @param redisKey
     * @param refreshFlag 是否每次刷新 false 定时刷新  true 每次刷新
     * @return
     */
    public String generalNumberVerify(String redisKey, boolean refreshFlag) {
        return generalVerify(redisKey, refreshFlag, false);
    }

    /**
     * 获取验证码， 仅包含数字
     *
     * @param redisKey
     * @param nums 验证码位数
     * @param refreshFlag 是否每次刷新 false 定时刷新  true 每次刷新
     * @return
     */
    public String generalNumberVerify(String redisKey, int nums, boolean refreshFlag) {
        return generalVerify(redisKey, nums, refreshFlag, false);
    }

    /**
     * 校验后台验证码
     *
     * @param verifyKey
     * @param verify
     */
    public void validateVerify(String verifyKey, String verify) {
        try {
            String redisVerify = (String) redisContext.get(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey));
            if (StrUtil.isBlank(redisVerify)) {
                throw new WeikbestException(ResultConstant.VERIFY_TIMEOUT_FAIL);
            }
            // 判断验证码是否正确
            if (!StrUtil.equalsAnyIgnoreCase(redisVerify, verify)) {
                throw new WeikbestException(ResultConstant.VERIFY_CHECK_FAIL);
            }
        } finally {
            // 验证码正确，删除验证码
            redisContext.del(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey));
        }
    }
    

}
