package com.weikbest.pro.saas.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * Created by 西瓜瓜
 * Date :2022/9/3
 * Description : 常量标识接口
 * Version :1.0
 */
public interface ConstantCode<T> {

    /**
     * @return 获取常量
     */
    T getCode();

    /**
     * 比较常量是否相等
     *
     * @param anotherCode 其他常量
     * @return 比较结果
     */
    boolean isEquals(T anotherCode);

    /**
     * 获取数据字典类型名称
     *
     * @return
     */
    default String dictTypeNumber() {
        String simpleName = this.getClass().getSimpleName();
        return StrUtil.lowerFirst(simpleName);
    }
}
