package com.weikbest.pro.saas.sys.param.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 第三方平台配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ThirdConfigVO对象", description = "第三方平台配置表  ")
public class ThirdConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty("微信支付-微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数")
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
    private String wxPaypayScorePermissionNotifyUrl;

    @ApiModelProperty("微信小程序-appId")
    private String wxMiniappAppId;

    @ApiModelProperty("微信小程序-secret")
    private String wxMiniappAppSecret;

    @ApiModelProperty("微信小程序-消息服务器配置的token")
    private String wxMiniappToken;

    @ApiModelProperty("微信小程序-消息服务器配置的EncodingAESKey")
    private String wxMiniappAesKey;

    @ApiModelProperty("微信小程序-小程序原始ID")
    private String wxMiniappOriginalId;

    @ApiModelProperty("微信小程序-消息格式，XML或者JSON，默认JSON")
    private String wxMiniappMsgDataFormat;

    @ApiModelProperty("微信小程序-云环境ID")
    private String wxMiniappColudEnv;

    @ApiModelProperty("阿里云物流appkey")
    private String aliyunWuliuAppkey;

    @ApiModelProperty("阿里云物流appcode")
    private String aliyunWuliuAppcode;

    @ApiModelProperty("阿里云物流appsecret")
    private String aliyunWuliuAppsecret;

    @ApiModelProperty("阿里云短信签名")
    private String aliyunSmsSignname;

    @ApiModelProperty("阿里云短信id")
    private String aliyunSmsAccesskeyId;

    @ApiModelProperty("阿里云短信secret")
    private String aliyunSmsAccesskeySecret;

    @ApiModelProperty("阿里云对象存储endpoint")
    private String aliyunOssFileEndpoint;

    @ApiModelProperty("阿里云对象存储keyid")
    private String aliyunOssFileKeyid;

    @ApiModelProperty("阿里云对象存储keysecret")
    private String aliyunOssFileKeysecret;

    @ApiModelProperty("阿里云对象存储bucketname")
    private String aliyunOssFileBucketname;

    @ApiModelProperty("阿里云OCRappsrcret")
    private String aliyunOcrAppsecret;

    @ApiModelProperty("阿里云OCRappcode")
    private String aliyunOcrAppcode;

    @ApiModelProperty("支付宝应用")
    private String alipayAppid;

    @ApiModelProperty("支付宝商户账号")
    private String alipayPid;

    @ApiModelProperty("支付宝私钥")
    private String alipayPrivateKey;

    @ApiModelProperty("支付宝公钥")
    private String alipayPublicKey;

    @ApiModelProperty("支付宝支付回调")
    private String alipayReturnUrl;

    @ApiModelProperty("应用公钥证书文件路径")
    private String alipayAppCertPath;

    @ApiModelProperty("支付宝公钥证书文件路径")
    private String alipayCertPath;

    @ApiModelProperty("支付宝CA根证书文件路径")
    private String alipayRootCertPath;

    @ApiModelProperty("字节跳动小程序id")
    private String toutiaoAppid;

    @ApiModelProperty("字节跳动小程序密钥")
    private String toutiaoAppsecret;

    @ApiModelProperty("字节跳动小程序签名")
    private String toutiaoSalt;

    @ApiModelProperty("字节跳动小程序支付回调地址")
    private String toutiaoNotifyUrl;

    @ApiModelProperty("字节跳动小程序退款回调地址")
    private String toutiaoRefundNotifyUrl;

    @ApiModelProperty("字节跳动小程序调用凭据")
    private String toutiaoAccessToken;

    @ApiModelProperty("字节跳动小程序调用凭据更新时间")
    private String toutiaoAccessTokenUpdateTime;


}