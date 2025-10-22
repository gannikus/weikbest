package com.weikbest.pro.saas.common.third.aliyun.logistics.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/20
 * <p>
 * 阿里云物流快递公司
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunWuliuCompanyResult {

    private String status;

    private String msg;

    private Map<String, String> result;
}
