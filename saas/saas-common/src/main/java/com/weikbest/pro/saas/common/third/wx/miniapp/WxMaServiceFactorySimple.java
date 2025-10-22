package com.weikbest.pro.saas.common.third.wx.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Service
public class WxMaServiceFactorySimple implements WxMaServiceFactory, InitializingBean {

    @Resource
    private WxMaProperties wxMaProperties;

    private volatile WxMaService wxMaService;

    @Override
    public WxMaService getWxMaService() {
        WxMaDefaultConfigImpl maConfig = new WxMaDefaultConfigImpl();
        maConfig.setAppid(wxMaProperties.getAppId());
        maConfig.setSecret(wxMaProperties.getSecret());
        maConfig.setToken(wxMaProperties.getToken());
        maConfig.setAesKey(wxMaProperties.getAesKey());
        maConfig.setMsgDataFormat(wxMaProperties.getMsgDataFormat());
        wxMaService.addConfig(maConfig.getAppid(), maConfig);
        wxMaService.switchoverTo(maConfig.getAppid());
        return wxMaService;
    }

    @Override
    public WxMaService switchoverTo(String appId) {
        return wxMaService.switchoverTo(appId);
    }

    @Override
    public WxMaService switchoverTo(WxMaConfig wxMaConfig) {
        if (StrUtil.isEmpty(wxMaConfig.getAppid()) || StrUtil.isEmpty(wxMaConfig.getSecret())) {
            throw new WeikbestException("请先配置wx-miniapp参数！");
        }

        wxMaService.addConfig(wxMaConfig.getAppid(), wxMaConfig);
        wxMaService.switchoverTo(wxMaConfig.getAppid());
        return wxMaService;
    }

    @Override
    public WxMaService switchoverTo(WxMaProperties wxMaProperties) {
        if (StrUtil.isEmpty(wxMaProperties.getAppId()) || StrUtil.isEmpty(wxMaProperties.getSecret())) {
            throw new WeikbestException("请先配置wx-miniapp参数！");
        }

        WxMaDefaultConfigImpl wxMaConfig = WxMapStruct.INSTANCE.converToWxMaDefaultConfigImpl(wxMaProperties);
        return switchoverTo(wxMaConfig);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        wxMaService = new WxMaServiceImpl();
    }
}
