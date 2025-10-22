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
import java.util.Date;

/**
 * <p>
 * 小程序订阅消息发送记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletSubscribeRecordDTO对象", description = "小程序订阅消息发送记录表")
public class AppletSubscribeRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "小程序ID不为空!")
    @ApiModelProperty(value = "小程序ID", required = true)
    private String appId;

    @ApiModelProperty("小程序页面路径")
    private String appletPage;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "短信模板ID不为空!")
    @ApiModelProperty(value = "短信模板ID", required = true)
    private Long subscribeConfigId;

    @NotBlank(message = "发送内容 不为空!")
    @ApiModelProperty(value = "发送内容 ", required = true)
    private String sendContent;

    @NotBlank(message = "发送参数映射 不为空!")
    @ApiModelProperty(value = "发送参数映射 ", required = true)
    private String sendParam;

    @NotBlank(message = "发送手机号 不为空!")
    @ApiModelProperty(value = "发送手机号 ", required = true)
    private String sendPhone;

    @NotBlank(message = "发送消息绑定路径不为空!")
    @ApiModelProperty(value = "发送消息绑定路径", required = true)
    private String bindUrl;

    @NotNull(message = "接收客户openid 不为空!")
    @ApiModelProperty(value = "接收客户openid ", required = true)
    private String receiptOpenid;

    @NotBlank(message = "接收客户名称 不为空!")
    @ApiModelProperty(value = "接收客户名称 ", required = true)
    private String receiptCustName;

    @NotBlank(message = "发送状态 不为空!")
    @ApiModelProperty(value = "发送状态 ", required = true)
    private String sendStatus;

    @ApiModelProperty(value = "发送时间  yyyy-MM-dd HH:mm:ss ", required = true)
    private Date sendTime;

    @NotBlank(message = "发送失败错误信息 不为空!")
    @ApiModelProperty(value = "发送失败错误信息 ", required = true)
    private String sendException;


    @NotBlank(message = "消息模板编码不为空!")
    @ApiModelProperty(value = "消息模板编码", required = true)
    private String number;

    @NotBlank(message = "消息模板名称不为空!")
    @ApiModelProperty(value = "消息模板名称", required = true)
    private String name;

    @NotBlank(message = "消息模板类型 2-一次性订阅 3-长期订阅不为空!")
    @ApiModelProperty(value = "消息模板类型 2-一次性订阅 3-长期订阅", required = true)
    private String subscribeType;
}