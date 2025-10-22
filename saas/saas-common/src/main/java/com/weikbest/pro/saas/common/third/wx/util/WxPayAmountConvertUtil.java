package com.weikbest.pro.saas.common.third.wx.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/15
 * <p>
 * 微信金额转换
 */
public class WxPayAmountConvertUtil {

    /**
     * 因子
     */
    private static final BigDecimal DIVISOR = new BigDecimal(100);

    /**
     * 将金额转换为微信金额 乘以100
     *
     * @param amount
     * @return
     */
    public static int multiplyConvert(BigDecimal amount) {
        return amount.multiply(new BigDecimal(100)).intValue();
    }


    /**
     * 将微信金额转换为金额 除以100，保留2位小数
     *
     * @param amount
     * @return
     */
    public static BigDecimal divideConvert(Integer amount) {
        return new BigDecimal(amount).divide(DIVISOR, MathContext.UNLIMITED);
    }
}
