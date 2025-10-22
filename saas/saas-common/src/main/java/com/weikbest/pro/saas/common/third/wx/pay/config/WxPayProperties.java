package com.weikbest.pro.saas.common.third.wx.pay.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2021/5/23
 * @project saas
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {

    /**
     * 微信小程序ID
     */
    private String appId;

    /**
     * 服务商模式下的子商户公众账号ID.
     */
    private String subAppId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * 企业支付密钥.
     */
    private String entPayKey;

    /**
     * 服务商模式下的子商户号.
     */
    private String subMchId;

    /**
     * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数.
     */
    private String notifyUrl;

    /**
     * 微信支付商户号证书路径 p12证书的位置
     */
    private String keyPath;

    /**
     * apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
     */
    private String privateKeyPath;

    /**
     * apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径.
     */
    private String privateCertPath;

    /**
     * APIv3密钥
     */
    private String apiV3Key;

    /**
     * apiV3 证书序列号值
     */
    private String certSerialNo;
    /**
     * 微信支付分serviceId
     */
    private String serviceId;

    /**
     * 微信支付分回调地址
     */
    private String payScoreNotifyUrl;


    /**
     * 微信支付分授权回调地址
     */
    private String payScorePermissionNotifyUrl;

}
