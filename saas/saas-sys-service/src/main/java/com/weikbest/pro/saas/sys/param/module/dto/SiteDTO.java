package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统站点设置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SiteDTO对象", description = "系统站点设置表")
public class SiteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商城名称")
    private String name;

    @ApiModelProperty(value = "商城图标链接")
    private String iconUrl;

    @ApiModelProperty(value = "商城后端logo链接")
    private String merchatLogoUrl;

    @ApiModelProperty(value = "后台业务管理系统logo链接")
    private String platformLogoUrl;

    @ApiModelProperty(value = "微信公众号二维码链接")
    private String wxGzhQrcodeUrl;

    @ApiModelProperty(value = "微信公众号appId")
    private String wxGzhAppId;

    @ApiModelProperty("微信客服链接")
    private String wxBusiness;
}