package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "AppletConfigDTO对象", description = "小程序配置表")
public class AppletConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序名称")
    private String appletName;

    @NotBlank(message = "小程序交易体验分 不为空!")
    @ApiModelProperty(value = "小程序交易体验分 0-100分", required = true)
    private Integer appletDealTrialScore;

    @NotBlank(message = "小程序健康状态 1-健康 2-中风险 3-高风险不为空!")
    @ApiModelProperty(value = "小程序健康状态 1-健康 2-中风险 3-高风险", required = true)
    private String appletHealthType;

    @ApiModelProperty("微信小程序类型 developer-开发版 trial-体验版；formal-正式版")
    private String wxMiniappType;

    @NotBlank(message = "微信小程序-appId不为空!")
    @ApiModelProperty(value = "微信小程序-appId", required = true)
    private String wxMiniappAppId;

    @NotBlank(message = "微信小程序-secret不为空!")
    @ApiModelProperty(value = "微信小程序-secret", required = true)
    private String wxMiniappAppSecret;

    @ApiModelProperty(value = "微信小程序-小程序原始ID")
    private String wxMiniappOriginalId;

}