package com.weikbest.pro.saas.common.third.aliyun.areas;

import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreas;

import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/3
 * <p>
 * 阿里云行政区划查询服务
 */
public interface AliyunAreasService {

    /**
     * 查询行政区划信息
     *
     * @param adcode 地区编码
     * @param level  地区级别
     * @return
     */
    List<AliyunAreas> queryAreas(Integer adcode, String level);

    /**
     * 查询全部行政区划信息
     *
     * @return
     */
    List<AliyunAreas> queryAllAreas();
}
