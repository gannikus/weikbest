package com.weikbest.pro.saas.common.third.wx.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Slf4j
public class WxPayServiceFactoryImpl implements WxPayServiceFactory {

    /**
     * 单例模式
     */
    private static final WxPayServiceFactoryImpl INSTANCE = new WxPayServiceFactoryImpl();

    private volatile WxPayService wxPayService;

    private WxPayServiceFactoryImpl() {
        wxPayService = new WxPayServiceImpl();
    }

    public static WxPayServiceFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public WxPayService switchoverTo(String mchId) {
        return wxPayService.switchoverTo(mchId);
    }

    @Override
    public WxPayService switchoverTo(WxPayConfig wxPayConfig) {
        wxPayService.addConfig(wxPayConfig.getMchId(), wxPayConfig);
        wxPayService.switchoverTo(wxPayConfig.getMchId());
        return wxPayService;
    }

    @Override
    public WxPayService switchoverTo(WxPayProperties wxPayProperties) {
        //        if (StrUtil.isEmpty(wxPayProperties.getAppId()) ||
//                StrUtil.isEmpty(wxPayProperties.getMchId()) ||
//                StrUtil.isEmpty(wxPayProperties.getMchKey()) ||
//                StrUtil.isEmpty(wxPayProperties.getApiV3Key()) ||
//                StrUtil.isEmpty(wxPayProperties.getKeyPath()) ||
//                StrUtil.isEmpty(wxPayProperties.getPrivateKeyPath()) ||
//                StrUtil.isEmpty(wxPayProperties.getPrivateCertPath())) {
//            throw new WeikbestException("请先配置wx.pay参数！");
//        }
        log.info("{} wxConfig : {}", wxPayProperties.getMchId(), wxPayProperties);

        WxPayConfig wxPayConfig = WxMapStruct.INSTANCE.converToWxPayConfig(wxPayProperties);
        return switchoverTo(wxPayConfig);
    }

    @Override
    public WxPayService getWxPayService() {
        return wxPayService;
    }
}
