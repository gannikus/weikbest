package com.weikbest.pro.saas.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author wisdomelon
 * @date 2020/7/2 0002
 * @project saas
 * @jdk 1.8
 */
public class OrderUtil {

    /**
     * 生成投诉单号 规则为年月日时分秒豪秒   + 2位随机数，共19位
     *
     * @return
     */
    public static String getOrderComplaintNumber() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("00");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(10));
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 3位随机数，共20位
     *
     * @return
     */
    public static String getOrderNumber() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("000");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(100));
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 3位随机数，共n+3位
     *
     * @param number 原始单号
     * @return
     */
    public static String getOrderAfterNumber(String number) {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("000");
        return number + df.format(random.nextInt(100));
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 5位随机数，共22位
     *
     * @return
     */
    public static String getNoncestr() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("00000");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(10000));
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 7位随机数，共24位
     *
     * @return
     */
    public static String getOutrefundno() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0000000");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(1000000));
    }

    /**
     * 生成领券请求号  规则为年月日时分秒豪秒   + 8位随机数，共25位
     *
     * @return
     */
    public static String getSendOutrefundNo() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("00000000");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(10000000));
    }

    /**
     * 生成请求号  规则为年月日时分秒豪秒   + 8位随机数，共25位
     *
     * @return
     */
    public static String getOutRequestNo() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("00000000");
        return new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(10000000));
    }
}
