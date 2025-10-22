package com.weikbest.pro.saas.common.third.alipay.config;

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
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 商户账号
     */
    private String pid;

    /**
     * 私钥
     */
    private String privatekey;

    /**
     * 公钥
     */
    private String publickey;

    /**
     * 支付回调
     */
    private String returnurl;

    /**
     * 应用公钥证书文件路径
     */
    private String appcertpath;

    /**
     * 支付宝公钥证书文件路径
     */
    private String certpath;

    /**
     * 支付宝CA根证书文件路径
     */
    private String rootcertpath;
}
