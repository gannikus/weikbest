package com.weikbest.pro.saas.sys.common.constant;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * 系统配置表常量
 */
public interface ConfigConstant {

    /**
     * 封禁IP
     */
    String PROJECTNAME = "projectName";

    /**
     * 封禁IP
     */
    String BAN = "ban";

    /**
     * 放开跨域访问的url
     */
    String ACCESSCONTROLALLOWORIGIN_URL = "AccessControlAllowOrigin.URL";

    /**
     * 查询物流间隔时长
     */
    String QUERY_LOGISTICS_INTERVAL_TIME = "queryLogisticsIntervalTime";

    /**
     * 微信支付回调地址
     */
    String WX_PAY_NOTIFY_URL = "wxPayNotifyUrl";

    /**
     * 微信退款回调地址
     */
    String WX_REFUND_NOTIFY_URL = "wxRefundNotifyUrl";

    /**
     * 微信合单支付回调地址
     */
    String WX_COMBINE_PAY_NOTIFY_URL = "wxCombinePayNotifyUrl";


    /**
     * 微信领券事件回调地址
     */
    String WX_BUSI_FAVOR_NOTIFY_URL = "wxBusiFavorNotifyUrl";

    /**
     * 腾讯广告获取accessToken地址
     */
    String TENCENT_ADS_OAUTH_TOKEN = "tencentAdsOauthToken";
}
