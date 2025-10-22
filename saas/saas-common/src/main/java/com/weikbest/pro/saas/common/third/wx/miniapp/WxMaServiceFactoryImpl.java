package com.weikbest.pro.saas.common.third.wx.miniapp;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Slf4j
public class WxMaServiceFactoryImpl implements WxMaServiceFactory {

    /**
     * 单例模式
     */
    private static final WxMaServiceFactoryImpl INSTANCE = new WxMaServiceFactoryImpl();

    private WxMaService wxMaService;

    private WxMaServiceFactoryImpl() {
        this.wxMaService = new WxMaServiceImpl();
    }

    /**
     * 构建WxMaServiceFactoryImpl对象
     *
     * @return
     */
    public static WxMaServiceFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public WxMaService getWxMaService() {
        return wxMaService;
    }

    @Override
    public WxMaService switchoverTo(String appId) {
        return wxMaService.switchoverTo(appId);
    }

    @Override
    public WxMaService switchoverTo(WxMaConfig wxMaConfig) {
        if (StrUtil.isEmpty(wxMaConfig.getAppid()) || StrUtil.isEmpty(wxMaConfig.getSecret())) {
            log.warn("使用WxMaService服务，未配置wx-miniapp参数，可能会导致参数异常！");
            return wxMaService;
        }

        wxMaService.addConfig(wxMaConfig.getAppid(), wxMaConfig);
        wxMaService.switchoverTo(wxMaConfig.getAppid());
        return wxMaService;
    }

    @Override
    public WxMaService switchoverTo(WxMaProperties wxMaProperties) {
        if (StrUtil.isEmpty(wxMaProperties.getAppId()) || StrUtil.isEmpty(wxMaProperties.getSecret())) {
            log.warn("使用WxMaService服务，未配置wx-miniapp参数，可能会导致参数异常！");
            return wxMaService;
        }

        WxMaDefaultConfigImpl wxMaConfig = WxMapStruct.INSTANCE.converToWxMaDefaultConfigImpl(wxMaProperties);
        return switchoverTo(wxMaConfig);
    }
}
