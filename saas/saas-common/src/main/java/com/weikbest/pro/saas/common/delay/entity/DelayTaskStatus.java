package com.weikbest.pro.saas.common.delay.entity;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/26
 */
public interface DelayTaskStatus {

    /**
     * 等待执行
     */
    String WAITING = "0";
    /**
     * 执行成功
     */
    String SUCCESS = "1";
    /**
     * 执行失败
     */
    String ERROR = "2";
    /**
     * 无法执行
     */
    String CANNOT = "9";
    /**
     * 删除任务
     */
    String DELETE = "-1";
}
