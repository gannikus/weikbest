package com.weikbest.pro.saas.common.third.aliyun.areas.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.area.url")
public class AliyunAreasUrlConfig {

    /**
     * 查询省份的URL
     */
    private String provinceUrl;

    /**
     * 查询城市的URL
     */
    private String cityUrl;

    /**
     * 查询县的URL
     */
    private String districtUrl;
}
