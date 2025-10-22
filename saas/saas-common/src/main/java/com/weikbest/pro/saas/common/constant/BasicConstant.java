package com.weikbest.pro.saas.common.constant;

/**
 * 基本常量类
 * @author Mr.Wamg
 */
public final class BasicConstant {
    /**
     * 数据状态String类型
     */
    public final static String STATE_0 = "0";
    public final static String STATE_1 = "1";
    public final static String STATE_2 = "2";
    public final static String STATE_3 = "3";
    public final static String STATE_4 = "4";
    public final static String STATE_5 = "5";
    public final static String STATE_6 = "6";
    public final static String STATE_7 = "7";
    public final static String STATE_10 = "10";
    public final static String STATE_30 = "30";
    public final static String STATE_99 = "99";

    /**
     * 数据状态Integer类型
     */
    public final static Integer INT_0 = 0;
    public final static Integer INT_1 = 1;
    public final static Integer INT_2 = 2;
    public final static Integer INT_3 = 3;
    public final static Integer INT_4 = 4;
    public final static Integer INT_8 = 8;

    /**
     * 切割符
     */
    public final static String CLEAVER = "ㅍдヤ㉤";

    /**
     * 页面banner默认图
     */
    public final static String JOINPAGE_BANNER = "http://saas-blindboxpai.oss-cn-hangzhou.aliyuncs.com/2023/05/08/merchant/1625384359190204416/e88fc44750f6432c8beab881cb070964e74df90ac1aea4a557bf8503fe7e0ef7.jpg";
    /**
     * 营销页默认图
     */
    public final static String JOINPAGE_OPEN = "https://saas-applet.oss-cn-hangzhou.aliyuncs.com/saas-mini-program/assets/20230509104039.gif";

    /**
     * 承诺服务常量类
     */
    public static class ServiceCommitment{
        /**
         * 急速售后
         */
        public final static String SERVICE_COMMITMENT_STATUS_1 = "急速售后";
        /**
         *全国部分包邮
         */
        public final static String SERVICE_COMMITMENT_STATUS_2 = "全国部分包邮";
        /**
         *七天无理由退换
         */
        public final static String SERVICE_COMMITMENT_STATUS_3 = "七天无理由退换";
        /**
         *坏损包退
         */
        public final static String SERVICE_COMMITMENT_STATUS_4 = "坏损包退";
        /**
         *消费者保障服务
         */
        public final static String SERVICE_COMMITMENT_STATUS_5 = "消费者保障服务";
        /**
         *正品保证
         */
        public final static String SERVICE_COMMITMENT_STATUS_6 = "正品保证";
        /**
         *假一赔十
         */
//        public final static String SERVICE_COMMITMENT_STATUS_7 = "假一赔十";
        public final static String SERVICE_COMMITMENT_STATUS_7 = "预售";

    }

    public static class PayType{
        /**
         * 微信支付
         */
        public final static String WX_PAY = "微信支付";
        /**
         * 货到付款
         */
        public final static String CASH_ON_DELIVERY = "货到付款";

    }

    public static class OrderNumber{
        /**
         * 主单号前缀
         */
        public final static String MAIN_NUMBER_START_WITH = "M";


    }

}
