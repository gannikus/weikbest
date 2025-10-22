package com.weikbest.pro.saas.common.third.wx.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 * 构建获取微信小程序服务类
 */
@Component
public class WxMaServiceBuildFactory {

    @Value("${thridconfig.properties}")
    private boolean thridconfigFlag;

    /**
     * 获取微信小程序服务类
     *
     * @return
     */
    public WxMaService initWxMaService() {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(WxMaServiceFactorySimple.class).getWxMaService();
        }
        return WxMaServiceFactoryImpl.getInstance().getWxMaService();
    }

    /**
     * 获取微信小程序服务类
     *
     * @param wxMaProperties
     * @return
     */
    public WxMaService wxMaService(WxMaProperties wxMaProperties) {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(WxMaServiceFactorySimple.class).getWxMaService();
        }
        return WxMaServiceFactoryImpl.getInstance().switchoverTo(wxMaProperties);
    }

    /**
     * 获取微信小程序服务类
     *
     * @param appId
     * @return
     */
    public WxMaService wxMaService(String appId) {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(WxMaServiceFactorySimple.class).switchoverTo(appId);
        }
        return WxMaServiceFactoryImpl.getInstance().switchoverTo(appId);
    }

    /**
     * 获取微信小程序服务类
     *
     * @param wxMaConfig
     * @return
     */
    public WxMaService wxMaService(WxMaConfig wxMaConfig) {
        if (thridconfigFlag) {
            WxMaService wxMaService = SpringContext.getInstance().getBean(WxMaServiceFactorySimple.class).getWxMaService();
            wxMaService.addConfig(wxMaConfig.getAppid(), wxMaConfig);
            wxMaService.switchoverTo(wxMaConfig.getAppid());
            return wxMaService;
        }
        return WxMaServiceFactoryImpl.getInstance().switchoverTo(wxMaConfig);
    }

}
