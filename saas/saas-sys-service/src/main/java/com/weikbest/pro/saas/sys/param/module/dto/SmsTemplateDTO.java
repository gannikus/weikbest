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
 * 短信模板表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SmsTemplateDTO对象", description = "短信模板表")
public class SmsTemplateDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "短信模板编码不为空!")
    @ApiModelProperty(value = "短信模板编码", required = true)
    private String number;

    @NotBlank(message = "短信模板名称不为空!")
    @ApiModelProperty(value = "短信模板名称", required = true)
    private String name;

    @NotBlank(message = "短信模板类型不为空!")
    @ApiModelProperty(value = "短信模板类型", required = true)
    private String smsType;

    @NotBlank(message = "短信模板内容不为空!")
    @ApiModelProperty(value = "短信模板内容", required = true)
    private String content;

    @NotBlank(message = "短信模板参数详情不为空!")
    @ApiModelProperty(value = "短信模板参数详情", required = true)
    private String params;

    @NotBlank(message = "短信绑定路径不为空!")
    @ApiModelProperty(value = "短信绑定路径", required = true)
    private String bindUrl;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}