package com.weikbest.pro.saas.common.third.aliyun.oss.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2020/4/5 0005
 * @project saas
 * @jdk 1.8
 * 阿里云OSS配置bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class AliyunOssConfig {

    private String endpoint;

    private String keyid;

    private String keysecret;

    private String bucketname;

}
