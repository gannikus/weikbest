package com.weikbest.pro.saas.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;

import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/15
 * <p>
 * 简单帮助类
 */
public class WeikbestObjectUtil {

    /**
     * long类型判空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Long value) {
        return !isNotEmpty(value);
    }

    /**
     * long类型判空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Long value) {
        return ObjectUtil.isNotEmpty(value) && value > WeikbestConstant.ZERO_LONG;
    }

    /**
     * int类型判空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Integer value) {
        return !isNotEmpty(value);
    }

    /**
     * int类型判空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Integer value) {
        return ObjectUtil.isNotEmpty(value) && value > WeikbestConstant.ZERO_INT;
    }


    /**
     * bigdecimal 类型判空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(BigDecimal value) {
        return !isNotEmpty(value);
    }

    /**
     * bigdecimal 类型判空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(BigDecimal value) {
        return ObjectUtil.isNotEmpty(value) && value.compareTo(BigDecimal.ZERO) > WeikbestConstant.ZERO_INT;
    }

    /**
     * 是否命中随机数
     *
     * @param rangeBigDecimal 随机数范围
     * @return 是否命中
     */
    public static boolean hitRandom(BigDecimal rangeBigDecimal) {
        // 随机数，[0,10000)中取一个
        int randomInt = RandomUtil.randomInt(100 * 100);
        // 随机数范围
        int rangeInt = WxPayAmountConvertUtil.multiplyConvert(rangeBigDecimal);
        return rangeInt >= randomInt;
    }
}
