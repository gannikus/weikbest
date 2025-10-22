package com.weikbest.pro.saas.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2020/6/25
 * @project saas
 * @jdk 1.8
 */
public class RegionCityUtil {

    /**
     * 分隔符
     */
    private static final String separator = "/";

    /***
     * 传入 省/市/区 得到一个省市区实例
     * @param regionCity
     * @return
     */
    public static RegionCity getRegionCity(String regionCity) {
        List<String> regionCityList = StrUtil.splitTrim(regionCity, separator);
        String province = CollectionUtil.get(regionCityList, 0);
        String city = CollectionUtil.get(regionCityList, 1);
        String area = CollectionUtil.get(regionCityList, 2);
        return new RegionCity(province, city, area);
    }

    /***
     * 传入 省 市 区 得到一个省/市/区
     * @param province
     * @param city
     * @param area
     * @return
     */
    public static String getRegionCity(String province, String city, String area) {
        return StrUtil.join(separator, province, city, area);
    }

    @Data
    @AllArgsConstructor
    public static class RegionCity {

        /**
         * 省
         */
        private String province;
        /**
         * 市
         */
        private String city;
        /**
         * 区
         */
        private String area;


    }
}


