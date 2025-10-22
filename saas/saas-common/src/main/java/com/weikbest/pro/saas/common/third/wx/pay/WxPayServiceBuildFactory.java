package com.weikbest.pro.saas.common.third.wx.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 * 构建获取微信支付服务类
 *
 */
@Component
public class WxPayServiceBuildFactory {

    @Value("${thridconfig.properties}")
    private boolean thridconfigFlag;

    /**
     * 获取微信支付服务类
     *
     * @return
     */
    public WxPayService initWxPayService() {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(WxPayServiceFactorySimple.class).getWxPayService();
        }
        return WxPayServiceFactoryImpl.getInstance().getWxPayService();
    }

    /**
     * 获取微信支付服务类
     *
     * @param wxPayProperties
     * @return
     */
    public WxPayService wxPayService(WxPayProperties wxPayProperties) {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(WxPayServiceFactorySimple.class).getWxPayService();
        }
        return WxPayServiceFactoryImpl.getInstance().switchoverTo(wxPayProperties);
    }

    /**
     * 根据配置切换微信支付服务类
     *
     * @param wxPayConfig
     * @return
     */
    public WxPayService wxPayService(WxPayConfig wxPayConfig) {
        if (thridconfigFlag) {
            WxPayService wxPayService = SpringContext.getInstance().getBean(WxPayServiceFactorySimple.class).getWxPayService();
            wxPayService.addConfig(wxPayConfig.getMchId(), wxPayConfig);
            wxPayService.switchoverTo(wxPayConfig.getMchId());
            return wxPayService;
        }
        return WxPayServiceFactoryImpl.getInstance().switchoverTo(wxPayConfig);
    }
}
