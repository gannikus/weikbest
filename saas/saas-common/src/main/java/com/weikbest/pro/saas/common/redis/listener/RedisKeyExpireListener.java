package com.weikbest.pro.saas.common.redis.listener;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听redis过期事件
 *
 * @author wisdomelon
 * @date 2021/5/15
 * @project saas
 * @jdk 1.8
 */
@Slf4j
//@Component
public class RedisKeyExpireListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpireListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 拿到key
        String redisKey = message.toString();
        log.info("监听Redis key过期，key：{}，channel：{}...", redisKey, new String(pattern));
        String redisKeyPrefix = redisKey.contains(RedisKey.SPLIT) ?
                StrUtil.sub(redisKey, 0, redisKey.indexOf(RedisKey.SPLIT)) : redisKey;

        ApplicationContext applicationContext = SpringContext.getInstance().getApplicationContext();
        Map<String, RedisKeyExpireCallback> beans = applicationContext.getBeansOfType(RedisKeyExpireCallback.class);
        boolean match = false;
        for (Map.Entry<String, RedisKeyExpireCallback> entry : beans.entrySet()) {
            RedisKeyExpireCallback redisKeyExpireCallback = entry.getValue();
            if (redisKeyPrefix.contains(redisKeyExpireCallback.getRedisKeyPrefix())) {
                // 如果key匹配成功，则执行指定的callback方法
                log.info("监听Redis key过期，key：{}，执行过期调用的类：{}...", redisKey, entry.getKey());
                match = true;
                redisKeyExpireCallback.callback(message, pattern);
            }
        }

        if (!match) {
            log.info("监听Redis key过期，key：{}，未找到该key对应的过期调用的类...", redisKey);
        }


    }
}
