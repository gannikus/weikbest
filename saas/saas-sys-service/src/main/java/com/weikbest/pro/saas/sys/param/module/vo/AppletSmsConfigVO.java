package com.weikbest.pro.saas.sys.param.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 小程序绑定短信配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletSmsConfigVO对象", description = "小程序绑定短信配置表")
public class AppletSmsConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("短信模板ID")
    private Long smsTemplateId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序配置表ID")
    private Long appletConfigId;

    @ApiModelProperty("小程序ID")
    private String appId;

    @ApiModelProperty("小程序页面路径")
    private String appletPage;

    @ApiModelProperty(value = "小程序链接参数内容")
    private String appletUrlParam;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}