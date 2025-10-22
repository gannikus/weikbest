package com.weikbest.pro.saas.common.third.bytedance.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "bytedance")
public class ByteDanceConfig {

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 小程序密钥
     */
    private String appsecret;

    /**
     * 小程序签名
     */
    private String salt;

    /**
     * 小程序支付回调地址
     */
    private String notifyurl;

    /**
     * 小程序退款回调地址
     */
    private String refundnotifyurl;
}
