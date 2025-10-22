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
@ApiModel(value = "AppletSubscribeRecordQO对象", description = "小程序订阅消息发送记录表")
public class AppletSubscribeRecordQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("小程序ID")
    private String appId;

    @ApiModelProperty("小程序页面路径")
    private String appletPage;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("短信模板ID")
    private Long subscribeConfigId;

    @ApiModelProperty("发送内容 ")
    private String sendContent;

    @ApiModelProperty("发送参数映射 ")
    private String sendParam;

    @ApiModelProperty("发送手机号 ")
    private String sendPhone;

    @ApiModelProperty("发送消息绑定路径")
    private String bindUrl;

    @ApiModelProperty("接收客户openid ")
    private String receiptOpenid;

    @ApiModelProperty("接收客户名称 ")
    private String receiptCustName;

    @ApiModelProperty("发送状态 ")
    private String sendStatus;

    @ApiModelProperty("发送时间  yyyy-MM-dd HH:mm:ss ")
    private Date sendTime;

    @ApiModelProperty("发送失败错误信息 ")
    private String sendException;


    @ApiModelProperty("消息模板编码")
    private String number;

    @ApiModelProperty("消息模板名称")
    private String name;

    @ApiModelProperty("消息模板类型 2-一次性订阅 3-长期订阅")
    private String subscribeType;
}