package com.weikbest.pro.saas.sys.param.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统支付商户号配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PayConfigQO对象", description = "系统支付商户号配置表")
public class PayConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("支付商户号类型  1-普通商户  3-特约商户")
    private String payConfigType;

    @ApiModelProperty("微信支付-微信公众号或者小程序等的appid")
    private String wxPayAppId;

    @ApiModelProperty("微信支付-服务商模式下的子商户公众账号ID")
    private String wxPaySubAppId;

    @ApiModelProperty("微信支付-商户号")
    private String wxPayMchId;

    @ApiModelProperty("微信支付-商户工商注册全名")
    private String wxPayMchName;

    @ApiModelProperty("微信支付-微信支付商户密钥")
    private String wxPayMchKey;

    @ApiModelProperty("微信支付-企业支付密钥")
    private String wxPayEntPayKey;

    @ApiModelProperty("微信支付-服务商模式下的子商户号")
    private String wxPaySubMchId;

    @ApiModelProperty("微信支付-微信支付异步回调地址，通知url必须为直接可访问的url，不能携带参数")
    private String wxPayNotifyUrl;

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

    @ApiModelProperty("微信支付-微信支付分")
    private String wxPayApiServiceId;

    @ApiModelProperty("微信支付-微信支付分回调地址")
    private String wxPayPayScoreNotifyUrl;

    @ApiModelProperty("微信支付-微信支付分授权回调地址")
    private String wxPayPayScorePermissionNotifyUrl;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}