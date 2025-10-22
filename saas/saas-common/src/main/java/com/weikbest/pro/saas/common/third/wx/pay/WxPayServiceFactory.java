package com.weikbest.pro.saas.common.third.wx.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
public interface WxPayServiceFactory {

    /**
     * 获取微信支付服务类
     *
     * @return
     */
    WxPayService getWxPayService();

    /**
     * 获取微信支付服务类
     *
     * @param mchId
     * @return
     */
    WxPayService switchoverTo(String mchId);

    /**
     * 获取微信支付服务类
     *
     * @param wxPayConfig
     * @return
     */
    WxPayService switchoverTo(WxPayConfig wxPayConfig);

    /**
     * 获取微信支付服务类
     *
     * @param wxPayProperties
     * @return
     */
    WxPayService switchoverTo(WxPayProperties wxPayProperties);
}
