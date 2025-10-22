package com.weikbest.pro.saas.common.coderule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * 编码规则服务
 */
public class CodeRuleUtil {

    private static final String SERIAL = "1";
    private static final String DATETIME_SERIAL = "2";


    /**
     * 初始化编码规则
     *
     * @param codeRuleType 编码规则类型
     * @param serial       流水号
     * @return 生成的编码规则值
     */
    public static String initCodeRule(String codeRuleType, String serial) {
        if (StrUtil.equals(codeRuleType, DATETIME_SERIAL)) {
            return DateUtil.format(new DateTime(), DatePattern.PURE_DATE_FORMAT) + serial;
        }
        return serial;
    }

    /**
     * 初始化编码规则流水号
     *
     * @param digit 位数
     * @return 生成的流水号值
     */
    public static String initSerial(int digit) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < digit; i++) {
            stringBuilder.append(WeikbestConstant.ZERO_STR);
        }
        return stringBuilder.toString();
    }

    /**
     * 编码规则转换流水号
     *
     * @param codeRuleType 编码规则类型
     * @param incrNum      已递增的编码
     * @param digit        位数
     * @return 真正的流水号
     */
    public static String convertNum(String codeRuleType, long incrNum, int digit) {
        String incrNumStr = String.valueOf(incrNum);
        if (StrUtil.equals(codeRuleType, DATETIME_SERIAL)) {
            return incrNumStr;
        }
        if (incrNumStr.length() >= digit) {
            return incrNumStr;
        }
        int gap = digit - incrNumStr.length();
        String gapStr = initSerial(gap);
        return gapStr + incrNumStr;
    }

    /**
     * 根据编码规则获取数字部分
     *
     * @param currNum 编码
     * @return 编码数字部分
     */
    public static long realNum(String currNum) {
        int index = 0;
        for (int i = 0; i < currNum.toCharArray().length; i++) {
            if (currNum.charAt(i) != WeikbestConstant.ZERO_CHAR) {
                index = i;
                break;
            }
        }
        return Long.parseLong(StrUtil.subSuf(currNum, index));
    }

    /**
     * 获取真正的编码长度
     *
     * @param codeRuleType
     * @param length
     * @return
     */
    public static int realDigit(String codeRuleType, int length) {
        if (StrUtil.equals(codeRuleType, DATETIME_SERIAL)) {
            return length - DatePattern.PURE_DATE_PATTERN.length();
        }
        return length;
    }
}
