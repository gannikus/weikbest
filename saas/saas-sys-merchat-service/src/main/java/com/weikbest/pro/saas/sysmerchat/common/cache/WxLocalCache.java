package com.weikbest.pro.saas.sysmerchat.common.cache;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.annotation.cache.LocalCache;
import com.weikbest.pro.saas.common.cache.ILocalCache;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.wx.miniapp.WxMaServiceBuildFactory;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import com.weikbest.pro.saas.common.third.wx.pay.WxPayServiceBuildFactory;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopThirdMapStruct;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.module.mapstruct.AppletConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.mapstruct.PayConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.mapstruct.ThirdConfigMapStruct;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 * <p>
 * 系统初始化将微信类的缓存都加载进来，方便后续切换
 */
@LocalCache(name = "wx")
@Slf4j
@Component
public class WxLocalCache implements ILocalCache {

    @Resource
    private WxPayServiceBuildFactory wxPayServiceBuildFactory;

    @Resource
    private WxMaServiceBuildFactory wxMaServiceBuildFactory;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private ShopThirdService shopThirdService;

    @Override
    public void loadCache() {
        loadWxPayCache();
        loadWxMaCache();
    }


    @Override
    public void refreshCache() {
        loadCache();
    }

    /**
     * 加载wx支付服务缓存
     */
    private void loadWxPayCache() {
        // 初始化微信支付服务类
        WxPayService wxPayService = wxPayServiceBuildFactory.initWxPayService();

        // 查询第三方平台记录
        ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(false);
        if (ObjectUtil.isNotNull(thirdConfig)) {
            WxPayProperties wxPayProperties = ThirdConfigMapStruct.INSTANCE.converToWxPayProperties(thirdConfig);
            if (StrUtil.isEmpty(wxPayProperties.getAppId()) ||
                    StrUtil.isEmpty(wxPayProperties.getMchId()) ||
                    StrUtil.isEmpty(wxPayProperties.getMchKey()) ||
                    StrUtil.isEmpty(wxPayProperties.getApiV3Key()) ||
                    StrUtil.isEmpty(wxPayProperties.getKeyPath()) ||
                    StrUtil.isEmpty(wxPayProperties.getPrivateKeyPath()) ||
                    StrUtil.isEmpty(wxPayProperties.getPrivateCertPath())) {
                log.info("thirdConfig 未配置wx.pay参数，跳过设置");
            } else {
                WxPayConfig wxPayConfig = WxMapStruct.INSTANCE.converToWxPayConfig(wxPayProperties);
                wxPayService.setConfig(wxPayConfig);
            }
        }

        // 查询系统支付记录表
        List<PayConfig> payConfigList = payConfigService.list();
        if(CollectionUtil.isNotEmpty(payConfigList)) {
            Map<String, WxPayConfig> payConfigMap = payConfigList.stream().map(PayConfigMapStruct.INSTANCE::converToWxPayConfig).collect(Collectors.toMap(WxPayConfig::getMchId, wxPayConfig -> wxPayConfig));
            if(CollectionUtil.isNotEmpty(payConfigMap)) {
                payConfigMap.forEach(wxPayService::addConfig);
            }
        }

        // 查询店铺表记录
        List<ShopThird> shopThirdList = shopThirdService.list();
        if(CollectionUtil.isNotEmpty(shopThirdList)) {
            Map<String, WxPayConfig> shopWxPayConfigMap = shopThirdList.stream().map(ShopThirdMapStruct.INSTANCE::converToWxPayConfig).collect(Collectors.toMap(WxPayConfig::getMchId, wxPayConfig -> wxPayConfig));
            if(CollectionUtil.isNotEmpty(shopWxPayConfigMap)) {
                shopWxPayConfigMap.forEach(wxPayService::addConfig);
            }
        }

        log.info("初始化微信支付服务类结束...");
    }

    /**
     * 加载wx 小程序服务缓存
     */
    private void loadWxMaCache() {
        WxMaService wxMaService = wxMaServiceBuildFactory.initWxMaService();

        // 查询第三方平台记录
        ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(false);
        if (ObjectUtil.isNotNull(thirdConfig)) {
            WxMaProperties wxMaProperties = ThirdConfigMapStruct.INSTANCE.converToWxMaProperties(thirdConfig);
            if (StrUtil.isEmpty(wxMaProperties.getAppId()) || StrUtil.isEmpty(wxMaProperties.getSecret())) {
                log.info("thirdConfig 未配置wx.miniapp参数，跳过设置");
            } else {
                WxMaDefaultConfigImpl wxMaConfig = WxMapStruct.INSTANCE.converToWxMaDefaultConfigImpl(wxMaProperties);
                wxMaService.setWxMaConfig(wxMaConfig);
            }
        }

        // 查询系统小程序配置表
        List<AppletConfig> appletConfigList = appletConfigService.list();
        if(CollectionUtil.isNotEmpty(appletConfigList)) {
            Map<String, WxMaConfig> wxMaConfigMap = appletConfigList.stream()
                    .map(AppletConfigMapStruct.INSTANCE::converToWxMaProperties)
                    .map(WxMapStruct.INSTANCE::converToWxMaDefaultConfigImpl)
                    .collect(Collectors.toMap(WxMaConfig::getAppid, wxMaConfig -> wxMaConfig));
            if(CollectionUtil.isNotEmpty(wxMaConfigMap)) {
                wxMaConfigMap.forEach(wxMaService::addConfig);
            }
        }

        log.info("初始化微信小程序服务类结束...");
    }

}
