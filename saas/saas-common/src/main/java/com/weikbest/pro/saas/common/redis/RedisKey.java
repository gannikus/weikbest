package com.weikbest.pro.saas.common.redis;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * @author wisdomelon
 * @date 2020/6/21
 * @project saas
 * @jdk 1.8
 */
public class RedisKey {

    /**
     * Redis的Key的分隔符
     */
    public static final String SPLIT = ":";

    /**
     * 登录用户的Token前缀
     */
    public static final String USER_TOKEN_KEY = "USER_TOKEN";

    /**
     * 登录用户的信息前缀
     */
    public static final String USER_DATA_HASH_KEY = "USER_DATA";

    /**
     * 后台验证码前缀
     */
    public static final String MANAGE_VERIFY_EXPIRE_KEY = "MANAGE_VERIFY";

    /**
     * 手机端验证码前缀，会过期
     */
    public static final String APP_VERIFY_EXPIRE_KEY = "APP_VERIFY";

    /**
     * APP登录用户的Token前缀
     */
//    public static final String APP_USER_TOKEN_KEY = "APP_USER_TOKEN";

    /**
     * APP登录用户的信息前缀
     */
//    public static final String APP_USER_DATA_HASH_KEY = "APP_USER_DATA";

    /**
     * 抖音小程序的access_token前缀，会过期
     */
//    public static final String APP_DY_ACCESS_TOKEN_EXPIRE_KEY = "APP_DY_ACCESS_TOKEN";

    /**
     * 微信小程序的access_token前缀，会过期
     */
//    public static final String APP_WX_ACCESS_TOKEN_EXPIRE_KEY = "APP_WX_ACCESS_TOKEN";

    /**
     * 编码规则编码的Redis前缀
     */
    public static final String MANAGE_CODE_RULE = "MANAGE_CODE_RULE";

    /**
     * 后台用户自定义控件前缀
     */
    public static final String MANAGE_USER_ACTIVEX_KEY = "MANAGE_USER_ACTIVEX";

    /**
     * 后台行政区划前缀
     */
    public static final String MANAGE_REGION_KEY = "MANAGE_REGION";

    /**
     * 后台同步物流redis锁前缀
     */
    public static final String MANAGE_LOGISTICSCOMPANY_KEY = "MANAGE_LOGISTICSCOMPANY";

    /**
     * 下单成功的弹幕的Key前缀 Redis前缀
     */
    public static final String APP_BARRAGE_KEY = "APP_BARRAGE_KEY";

    /**
     * 第三方配置存储key前缀，会过期
     */
    public static final String THIRD_CONFIG_EXPIRE_KEY = "THIRD_CONFIG";


    /**
     * 系统结算规则存储key前缀，会过期
     */
    public static final String SETTLE_CONFIG_EXPIRE_KEY = "SETTLE_CONFIG";

    /**
     * 系统交易规则存储key前缀，会过期
     */
    public static final String DEAL_CONFIG_EXPIRE_KEY = "DEAL_CONFIG";

    /**
     * 商品规范存储key前缀，会过期
     */
    public static final String PROD_STANDARD_EXPIRE_KEY = "PROD_STANDARD";

    /**
     * 系统站点设置存储key前缀，会过期
     */
    public static final String SITE_EXPIRE_KEY = "SITE";

    /**
     * 客户购买记录的set key前缀
     */
    public static final String APP_CUSTBUY_RECORD_SET_KEY = "APP_CUSTBUY_RECORD_SET";

    /**
     * 客户购买记录的set 中的key前缀
     */
    public static final String APP_CUSTBUY_RECORD_KEY = "APP_CUSTBUY_RECORD";

    /**
     * 库存锁定的set key前缀
     */
    public static final String APP_LOCK_STOCK_SET_KEY = "APP_LOCK_STOCK_SET";

    /**
     * 库存锁定的set中的key前缀，会过期
     */
    public static final String APP_LOCK_STOCK_EXPIRE_KEY = "APP_LOCK_STOCK";

    /**
     * 延时任务的 Redis锁前缀
     */
    public static final String DELAY_QUEUE_HASH_KEY = "DELAY_QUEUE_HASH";
    /**
     *广告监测key前缀，会过期
     */
    public static final String AD_CLICK_MONITORING_KEY = "AD_CLICK_MONITORING_KEY";

    /**
     * 生成一个Redis中的key
     *
     * @param keyPrefix
     * @param keys
     * @return
     */
    public static String generalKey(String keyPrefix, Object... keys) {

        StrBuilder strBuilder = new StrBuilder(keyPrefix).append(SPLIT);

        String keySuffix = StrUtil.join(SPLIT, keys);

        return strBuilder.append(keySuffix).toString();

    }

    /**
     * redis锁前缀
     */
    public static class Lock {
        /**
         * 用户token锁
         */
        public static final String LOCK_USER_TOKEN = "LOCK_USER_TOKEN";
        /**
         * 编码规则Redis锁前缀
         */
        public static final String LOCK_CODE_RULE = "LOCK_CODE_RULE";

        /**
         * 后台行政区划redis锁前缀
         */
        public static final String LOCK_REGION_KEY = "LOCK_REGION";

        /**
         * 后台同步物流redis锁前缀
         */
        public static final String LOCK_LOGISTICSCOMPANY_KEY = "LOCK_LOGISTICSCOMPANY";

        /**
         * 订单 锁前缀
         */
        public static final String LOCK_ORDER_KEY = "LOCK_ORDER";

        /**
         * 店铺第三方数据的 锁前缀
         */
        public static final String LOCK_REMOVE_SHOP_THIRD_RECEIVE_KEY = "LOCK_REMOVE_SHOP_THIRD_RECEIVE_KEY";

        /**
         * 订单售后 锁前缀
         */
        public static final String LOCK_ORDER_AFTER_SALE_KEY = "LOCK_ORDER_AFTER_SALE";

        /**
         * 优惠券 锁前缀
         */
        public static final String LOCK_COUPON_KEY = "LOCK_COUPON";

        /**
         * 添加支付回调的 锁前缀
         */
        public static final String LOCK_PAY_NOTIFY_KEY = "LOCK_PAY_NOTIFY";

        /**
         * 添加退款回调的 锁前缀
         */
        public static final String LOCK_REFUNDNOTIFY_KEY = "LOCK_REFUNDNOTIFY";

        /**
         * 库存更新的Key前缀 Redis锁前缀
         */
        public static final String LOCK_APP_STOCK_KEY = "LOCK_APP_STOCK";

        /**
         * 抖音小程序的access_token 锁前缀
         */
        public static final String LOCK_APP_DY_ACCESS_TOKEN_KEY = "LOCK_APP_DY_ACCESS_TOKEN";

        /**
         * 微信小程序的access_token 锁前缀
         */
        public static final String LOCK_APP_WX_ACCESS_TOKEN_KEY = "LOCK_APP_WX_ACCESS_TOKEN";

        /**
         * 延时队列的Key前缀 Redis锁前缀
         */
        public static final String LOCK_DELAY_KEY = "LOCK_DELAY";

        /**
         * 客户钱包的Key前缀 Redis锁前缀
         */
        public static final String LOCK_CUSTOMER_WITHDRAW_RECORD_KEY = "LOCK_CUSTOMER_WITHDRAW_RECORD";

        /**
         * 订单状态的Key前缀 Redis锁前缀
         */
        public static final String LOCK_ORDER_STATUS_KEY = "LOCK_ORDER_STATUS";

        /**
         * 投诉单的Key前缀 Redis锁前缀
         */
        public static final String LOCK_ORDER_COMPLAINT_KEY = "LOCK_ORDER_COMPLAINT";

        /**
         * 优惠券领券Key前缀 Redis锁前缀
         */
        public static final String LOCK_COUPON_RESTRICT_KEY = "LOCK_COUPON_RESTRICT_KEY";

        /**
         * 客户绑定商户前缀 Redis锁前缀
         */
        public static final String LOCK_CUST_BUSINESS_BIND_KEY = "LOCK_CUST_BUSINESS_BIND_KEY";
    }

    /**
     * 延时任务前缀
     */
    public static class DelayTask {

        /**
         * 订单待付款超30分钟 延时任务前缀
         */
        public static final String DELAY_ORDER_WAIT_PAY_TIMEOUT_KEY = "DELAY_ORDER_WAIT_PAY_TIMEOUT";

        /**
         * 订单待付款超24小时 延时任务前缀
         */
        public static final String DELAY_ORDER_PAY_TIMEOUT_KEY = "DELAY_ORDER_PAY_TIMEOUT";

        /**
         * 订单发货超过24小时 延时任务前缀
         */
        public static final String DELAY_ORDER_DELIVER_TIMEOUT_KEY = "DELAY_ORDER_DELIVER_TIMEOUT";

        /**
         * 订单发货后客户确认收货超时 延时任务前缀
         */
        public static final String DELAY_ORDER_DELIVER_CUSTOMER_TIMEOUT_KEY = "DELAY_ORDER_DELIVER_CUSTOMER_TIMEOUT";

        /**
         * 订单已发货客户未处理超时 超过15天 延时任务前缀
         */
        public static final String DELAY_ORDER_FINISH_TIMEOUT_KEY = "DELAY_ORDER_FINISH_TIMEOUT";

        /**
         * 订单售后售后单商家处理客户申请过期延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_BUSINESS_EXECUTETIMEOUT_KEY = "DELAY_ORDER_AFTER_SALE_BUSINESS_EXECUTETIMEOUT";

        /**
         * 订单售后售后单商家拒绝后客户处理延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_CUSTOMER_EXECUTETIMEOUT_KEY = "DELAY_ORDER_AFTER_SALE_CUSTOMER_EXECUTETIMEOUT";

        /**
         * 售后单商家同意后客户发货处理过期延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_CUSTOMER_DELIVERYTIMEOUT_KEY = "DELAY_ORDER_AFTER_SALE_CUSTOMER_DELIVERYTIMEOUT";

        /**
         * 售后单商家拒绝收货后客户发货处理过期延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_BUSINESS_REJECTDELIVERY_CUSTOMER_EXECUTETIMEOUT_KEY = "DELAY_ORDER_AFTER_SALE_BUSINESS_REJECTDELIVERY_CUSTOMER_EXECUTETIMEOUT";

        /**
         * 售后单售后单商家确认收货后处理过期延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_BUSINESS_DELIVERAFTER_YTIMEOUT_KEY = "DELAY_ORDER_AFTER_SALE_BUSINESS_DELIVERAFTER_YTIMEOUT";

        /**
         * 售后单客户寄回商品后商家确认收货处理过期延时任务前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_BUSINESS_CONFIRM_CUSTOMER_DELIVERY_TIMEOUT_KEY = "DELAYORDER_AFTER_SALE_BUSINESS_CONFIRM_CUSTOMER_DELIVERY_TIMEOUT";

        /**
         * 优惠券活动生效延时任务前缀
         */
        public static final String DELAY_COUPON_EVENT_START_KEY = "DELAY_COUPON_EVENT_START_KEY";

        /**
         * 优惠券活动结束延时任务前缀
         */
        public static final String DELAY_COUPON_EVENT_END_KEY = "DELAY_COUPON_EVENT_END_KEY";

        /**
         * 投诉单商家处理过期延时任务
         */
        public static final String ORDER_COMPLAINT_BUSINESS_EXECUTE_TIMEOUT_KEY = "ORDER_COMPLAINT_BUSINESS_EXECUTE_TIMEOUT";

        /**
         * 优惠券领券状态已过期延时任务前缀
         */
        public static final String DELAY_COUPON_RESTRICT_TYPE_EXPIRED_KEY = "DELAY_COUPON_RESTRICT_TYPE_EXPIRED_KEY";

        /**
         * 优惠券领用状态未使用队列前缀
         */
        public static final String DELAY_COUPON_RESTRICT_TYPE_NOTUSED_KEY = "DELAY_COUPON_RESTRICT_TYPE_NOTUSED_KEY";

        /**
         *客户绑定商户有效期到期队列前缀
         */
        public static final String ORDER_CUST_BUSINESS_BIND_KEY = "ORDER_CUST_BUSINESS_BIND_KEY";


        /**
         * 订单资金解冻超过小时数时解冻队列前缀
         */
        public static final String ORDER_FUND_RELEASE_HOUR_TIMEOUT_KEY = "ORDER_FUND_RELEASE_HOUR_TIMEOUT";

        /**
         * 订单分账押金回退清算队列前缀
         */
        public static final String ORDER_SETTLEMENT_REBATE_OF_DEPOSIT_DAY_TIMEOUT_KEY = "ORDER_SETTLEMENT_REBATE_OF_DEPOSIT_DAY_TIMEOUT";
        /**
         * 退款任务队列前缀
         */
        public static final String DELAY_ORDER_AFTER_SALE_REFUND_KEY ="DELAY_ORDER_AFTER_SALE_REFUND_KEY";
    }


}
