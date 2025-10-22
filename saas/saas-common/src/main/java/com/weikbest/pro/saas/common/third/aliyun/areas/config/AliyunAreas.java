package com.weikbest.pro.saas.common.third.aliyun.areas.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunAreas {

    /**
     * 地区编码
     */
    private Integer adcode;

    /**
     * 人口总数
     */
    private Integer people_count_2010;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 名称
     */
    private String name;

    /**
     * 级别
     */
    private String level;

    /**
     * 父级名称
     */
    private String parent;

    /**
     * 地区父编码
     */
    @JsonIgnore
    private Integer parentAdcode;
}
