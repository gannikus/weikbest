package com.weikbest.pro.saas.sys.param.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "AppletSmsConfigDTO对象", description = "小程序绑定短信配置表")
public class AppletSmsConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "短信模板ID不为空!")
    @ApiModelProperty(value = "短信模板ID", required = true)
    private Long smsTemplateId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "小程序配置表ID不为空!")
    @ApiModelProperty(value = "小程序配置表ID", required = true)
    private Long appletConfigId;

    @NotBlank(message = "小程序ID不为空!")
    @ApiModelProperty(value = "小程序ID", required = true)
    private String appId;

    @NotBlank(message = "小程序页面路径不为空!")
    @ApiModelProperty(value = "小程序页面路径", required = true)
    private String appletPage;

    @NotBlank(message = "小程序链接参数内容不为空!")
    @ApiModelProperty(value = "小程序链接参数内容", required = true)
    private String appletUrlParam;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}