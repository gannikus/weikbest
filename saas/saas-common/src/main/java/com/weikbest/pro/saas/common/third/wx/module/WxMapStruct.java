package com.weikbest.pro.saas.common.third.wx.module;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Mapper
public interface WxMapStruct {

    WxMapStruct INSTANCE = Mappers.getMapper(WxMapStruct.class);

    /**
     * wxMaProperties转换为WxMaDefaultConfigImpl
     *
     * @param wxMaProperties wxMaProperties
     * @return WxMaDefaultConfigImpl
     */
    @Mapping(target = "appid", source = "appId")
    WxMaDefaultConfigImpl converToWxMaDefaultConfigImpl(WxMaProperties wxMaProperties);

    /**
     * wxPayProperties转换为WxPayConfig
     *
     * @param wxPayProperties wxPayProperties
     * @return WxPayConfig
     */
    WxPayConfig converToWxPayConfig(WxPayProperties wxPayProperties);

}
