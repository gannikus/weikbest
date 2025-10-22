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
@ApiModel(value = "SiteVO对象", description = "系统站点设置表")
public class SiteVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("商城名称")
    private String name;

    @ApiModelProperty("商城图标链接")
    private String iconUrl;

    @ApiModelProperty("商城后端logo链接")
    private String merchatLogoUrl;

    @ApiModelProperty("后台业务管理系统logo链接")
    private String platformLogoUrl;

    @ApiModelProperty("微信公众号二维码链接")
    private String wxGzhQrcodeUrl;

    @ApiModelProperty("微信公众号appId")
    private String wxGzhAppId;

    @ApiModelProperty("平台微信客服")
    private String wxBusiness;


}