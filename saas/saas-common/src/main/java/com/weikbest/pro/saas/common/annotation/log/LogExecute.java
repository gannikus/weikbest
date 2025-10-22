package com.weikbest.pro.saas.common.annotation.log;

import org.slf4j.Logger;

import java.lang.reflect.Method;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 */
public interface LogExecute {

    /**
     * 执行日志记录
     *
     * @param logger       日志源
     * @param className
     * @param method
     * @param ipAddress
     * @param userId
     * @param mobileDevice
     * @param traceid
     */
    void execute(Logger logger, String className, Method method, String ipAddress, Long userId, boolean mobileDevice, String traceid);
}
