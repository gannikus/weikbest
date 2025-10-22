package com.weikbest.pro.saas.common.third.aliyun.sms.function;

import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunOneSmsParam;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@FunctionalInterface
public interface AliyunSmsParamFunc {

    /**
     * 构建请求参数
     *
     * @return
     */
    AliyunOneSmsParam buildAliyunSmsParam(AliyunOneSmsParam aliyunOneSmsParam);
}
