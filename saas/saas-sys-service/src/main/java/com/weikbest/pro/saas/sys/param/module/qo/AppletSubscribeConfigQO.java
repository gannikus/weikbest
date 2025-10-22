package com.weikbest.pro.saas.sys.param.module.qo;

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
@ApiModel(value = "AppletSubscribeConfigQO对象", description = "小程序订阅消息配置表")
public class AppletSubscribeConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序配置表ID")
    private Long appletConfigId;

    @ApiModelProperty("小程序ID")
    private String appId;

    @ApiModelProperty("小程序页面路径")
    private String appletPage;

    @ApiModelProperty("消息模板编码")
    private String number;

    @ApiModelProperty("消息模板名称")
    private String name;

    @ApiModelProperty("消息模板类型 2-一次性订阅 3-长期订阅")
    private String subscribeType;

    @ApiModelProperty("消息模板内容")
    private String content;

    @ApiModelProperty("消息模板参数详情")
    private String params;

    @ApiModelProperty("消息绑定路径，多个路径用,分割")
    private String bindUrl;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}