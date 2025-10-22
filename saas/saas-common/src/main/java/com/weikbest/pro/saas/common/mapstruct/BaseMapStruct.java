package com.weikbest.pro.saas.common.mapstruct;

import org.springframework.beans.BeanUtils;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/9/2
 */
public interface BaseMapStruct {

    /**
     * 值复制
     *
     * @param source 源
     * @param target 目标
     */
    default void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
