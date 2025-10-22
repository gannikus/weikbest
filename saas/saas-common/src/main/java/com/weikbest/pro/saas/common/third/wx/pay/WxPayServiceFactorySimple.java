package com.weikbest.pro.saas.common.third.wx.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Service
public class WxPayServiceFactorySimple implements WxPayServiceFactory, InitializingBean {

    @Autowired
    private WxPayProperties wxPayProperties;

    private volatile WxPayService wxPayService;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (wxPayService == null) {
            synchronized (this) {
                if (wxPayService == null) {
                    wxPayService = new WxPayServiceImpl();
                }
            }
        }
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
        WxPayConfig wxPayConfig = WxMapStruct.INSTANCE.converToWxPayConfig(wxPayProperties);
        return switchoverTo(wxPayConfig);
    }

    @Override
    public WxPayService getWxPayService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(this.wxPayProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(this.wxPayProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(this.wxPayProperties.getMchKey()));
        payConfig.setApiV3Key(StringUtils.trimToNull(this.wxPayProperties.getApiV3Key()));
        payConfig.setKeyPath(StringUtils.trimToNull(this.wxPayProperties.getKeyPath()));
        payConfig.setPrivateKeyPath(StringUtils.trimToNull(this.wxPayProperties.getPrivateKeyPath()));
        payConfig.setPrivateCertPath(StringUtils.trimToNull(this.wxPayProperties.getPrivateCertPath()));
        wxPayService.addConfig(payConfig.getMchId(), payConfig);
        wxPayService.switchoverTo(payConfig.getMchId());
        return wxPayService;
    }
}
