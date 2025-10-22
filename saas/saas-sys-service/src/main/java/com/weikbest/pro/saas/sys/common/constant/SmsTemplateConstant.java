package com.weikbest.pro.saas.sys.common.constant;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 * 短信模板绑定路径常量
 */
public interface SmsTemplateConstant {

    /**
     * 验证码
     */
    String VERIFYPHONE = "/verifyPhone";

    /**
     * 待支付订单
     */
    String WAITPAYTIMEOUT = "/waitPayTimeout";

    /**
     * 下单成功
     */
    String ORDERSUCCESS = "/orderSuccess";

    /**
     * 订单已发货
     */
    String ORDERDELIVERSUCCESS = "/orderDeliverSuccess";
}
