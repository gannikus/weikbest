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
 * 小程序订阅消息配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletSubscribeConfigDTO对象", description = "小程序订阅消息配置表")
public class AppletSubscribeConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "小程序配置表ID不为空!")
    @ApiModelProperty(value = "小程序配置表ID", required = true)
    private Long appletConfigId;

    @NotBlank(message = "小程序ID不为空!")
    @ApiModelProperty(value = "小程序ID", required = true)
    private String appId;

    @ApiModelProperty("小程序页面路径")
    private String appletPage;

    @NotBlank(message = "消息模板编码不为空!")
    @ApiModelProperty(value = "消息模板编码", required = true)
    private String number;

    @NotBlank(message = "消息模板名称不为空!")
    @ApiModelProperty(value = "消息模板名称", required = true)
    private String name;

    @NotBlank(message = "消息模板类型 2-一次性订阅 3-长期订阅不为空!")
    @ApiModelProperty(value = "消息模板类型 2-一次性订阅 3-长期订阅", required = true)
    private String subscribeType;

    @NotBlank(message = "消息模板内容不为空!")
    @ApiModelProperty(value = "消息模板内容", required = true)
    private String content;

    @NotBlank(message = "消息模板参数详情不为空!")
    @ApiModelProperty(value = "消息模板参数详情", required = true)
    private String params;

    @NotBlank(message = "消息绑定路径，多个路径用,分割不为空!")
    @ApiModelProperty(value = "消息绑定路径，多个路径用,分割", required = true)
    private String bindUrl;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}