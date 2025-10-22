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
 * 小程序配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletConfigVO对象", description = "小程序配置表")
public class AppletConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("小程序名称")
    private String appletName;

    @ApiModelProperty("小程序类型 1-微信小程序")
    private String appletType;

    @ApiModelProperty(value = "小程序交易体验分 0-100分")
    private Integer appletDealTrialScore;

    @ApiModelProperty("小程序健康状态 1-健康 2-中风险 3-高风险")
    private String appletHealthType;

    @ApiModelProperty("微信小程序类型 developer-开发版 trial-体验版；formal-正式版")
    private String wxMiniappType;

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

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}