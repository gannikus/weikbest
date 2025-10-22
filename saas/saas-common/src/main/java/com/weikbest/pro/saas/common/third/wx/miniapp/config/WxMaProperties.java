package com.weikbest.pro.saas.common.third.wx.miniapp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {

    /**
     * 小程序原始ID
     */
    protected String originalId;
    /**
     * 设置微信小程序的appId
     */
    private String appId;
    /**
     * 设置微信小程序的Secret
     */
    private String secret;
    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;
    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;
    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

    /**
     * 云环境ID
     */
    private String cloudEnv;

}
