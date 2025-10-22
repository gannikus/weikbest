package com.weikbest.pro.saas.common.third.aliyun.areas.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunAreasResult {

    private int total;

    private List<AliyunAreas> rows;
}
