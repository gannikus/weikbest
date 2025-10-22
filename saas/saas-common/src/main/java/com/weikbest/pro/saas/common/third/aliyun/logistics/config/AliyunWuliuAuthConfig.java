package com.weikbest.pro.saas.common.third.aliyun.logistics.config;

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
@ConfigurationProperties(prefix = "aliyun.wuliu.auth")
public class AliyunWuliuAuthConfig {

    private String appcode;

    private String appkey;

    private String appsecret;
}
