package com.weikbest.pro.saas.merchat.shop.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 店铺第三方平台拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopThirdQO对象", description = "店铺第三方平台拆分表")
public class ShopThirdQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否添加分账接收方 0-否1-是")
    private String isAddReceive;

    @ApiModelProperty("微信商户类型 1-普通商户")
    private String wxMchType;

    @ApiModelProperty("微信支付-商户号")
    private String wxPayMchId;

    @ApiModelProperty("微信支付-微信支付商户密钥")
    private String wxPayMchKey;

    @ApiModelProperty("微信支付-微信支付商户号证书路径 p12证书的位置")
    private String wxPayKeyPath;

    @ApiModelProperty("微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径")
    private String wxPayPrivateKeyPath;

    @ApiModelProperty("微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径")
    private String wxPayPrivateCertPath;

    @ApiModelProperty("微信支付-APIv3密钥")
    private String wxPayApiV3Key;

    @ApiModelProperty("微信支付-APIv3 证书序列号值")
    private String wxPayApiCertSerialNo;

    @ApiModelProperty("微信商户号证书序列号生效时间")
    private Date wxPayMchEffectiveTime;

    @ApiModelProperty("微信商户号证书序列号失效时间")
    private Date wxPayMchExpireTime;

    @ApiModelProperty("微信平台证书序列号")
    private String wxPlatformSerialNo;

    @ApiModelProperty("微信平台证书序列号生效时间")
    private Date wxPlatformEffectiveTime;

    @ApiModelProperty("微信平台证书序列号失效时间")
    private Date wxPlatformExpireTime;


}