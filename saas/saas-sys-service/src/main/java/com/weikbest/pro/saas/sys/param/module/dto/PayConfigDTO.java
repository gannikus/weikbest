package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "PayConfigDTO对象", description = "系统支付商户号配置表")
public class PayConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "微信支付-商户号不为空!")
    @ApiModelProperty(value = "微信支付-商户号", required = true)
    private String wxPayMchId;

    @ApiModelProperty("微信支付-微信支付商户APIv2密钥")
    private String wxPayMchKey;

    @NotBlank(message = "微信支付-微信支付商户号证书路径 p12证书的位置不为空!")
    @ApiModelProperty(value = "微信支付-微信支付商户号证书路径 p12证书的位置", required = true)
    private String wxPayKeyPath;

    @NotBlank(message = "微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径不为空!")
    @ApiModelProperty(value = "微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径", required = true)
    private String wxPayPrivateKeyPath;

    @NotBlank(message = "微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径不为空!")
    @ApiModelProperty(value = "微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径", required = true)
    private String wxPayPrivateCertPath;

    @NotBlank(message = "微信支付-APIv3密钥不为空!")
    @ApiModelProperty(value = "微信支付-APIv3密钥", required = true)
    private String wxPayApiV3Key;

    @NotBlank(message = "微信支付-APIv3 证书序列号值不为空!")
    @ApiModelProperty(value = "微信支付-APIv3 证书序列号值", required = true)
    private String wxPayApiCertSerialNo;

    @NotNull(message = "微信商户号证书序列号生效时间不为空!")
    @ApiModelProperty(value = "微信商户号证书序列号生效时间", required = true)
    private Date wxPayMchEffectiveTime;

    @NotNull(message = "微信商户号证书序列号失效时间不为空!")
    @ApiModelProperty(value = "微信商户号证书序列号失效时间", required = true)
    private Date wxPayMchExpireTime;

    @ApiModelProperty("微信支付-微信在线申请新证书时填写的操作密码")
    private String wxOperatePassword;

}