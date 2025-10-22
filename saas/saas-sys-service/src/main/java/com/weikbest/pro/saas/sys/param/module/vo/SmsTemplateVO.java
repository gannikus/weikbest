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
@ApiModel(value = "SmsTemplateVO对象", description = "短信模板表")
public class SmsTemplateVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("短信模板编码")
    private String number;

    @ApiModelProperty("短信模板名称")
    private String name;

    @ApiModelProperty("短信模板类型")
    private String smsType;

    @ApiModelProperty("短信模板内容")
    private String content;

    @ApiModelProperty("短信模板参数详情")
    private String params;

    @ApiModelProperty("短信绑定路径")
    private String bindUrl;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}