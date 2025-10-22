package com.weikbest.pro.saas.common.third.wx.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
public interface WxMaServiceFactory {

    /**
     * 获取微信小程序服务类
     *
     * @return
     */
    WxMaService getWxMaService();

    /**
     * 获取微信小程序服务类
     *
     * @param appId
     * @return
     */
    WxMaService switchoverTo(String appId);


    /**
     * 获取微信支付服务类
     *
     * @param wxMaConfig
     * @return
     */
    WxMaService switchoverTo(WxMaConfig wxMaConfig);

    /**
     * 获取微信小程序服务类
     *
     * @param wxMaProperties
     * @return
     */
    WxMaService switchoverTo(WxMaProperties wxMaProperties);

}
