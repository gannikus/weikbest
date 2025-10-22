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
import java.util.Date;

/**
 * <p>
 * 短信发送记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SmsRecordVO对象", description = "短信发送记录表")
public class SmsRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("短信模板ID")
    private Long smsTemplateId;

    @ApiModelProperty("短信模板编码")
    private String smsNumber;

    @ApiModelProperty("短信模板名称")
    private String smsName;

    @ApiModelProperty("短信模板类型")
    private String smsType;

    @ApiModelProperty("发送内容 ")
    private String sendContent;

    @ApiModelProperty("发送参数映射 ")
    private String sendParam;

    @ApiModelProperty("发送手机号 ")
    private String sendPhone;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("接收用户ID ")
    private Long receiptUserid;

    @ApiModelProperty("接收用户名称 ")
    private String receiptUsername;

    @ApiModelProperty("发送状态 ")
    private String sendStatus;

    @ApiModelProperty("发送时间  yyyy-MM-dd HH:mm:ss ")
    private Date sendTime;

    @ApiModelProperty("发送返回报文 ")
    private String sendResponse;

    @ApiModelProperty("关联ID ")
    private String relevanceId;

    @ApiModelProperty("关联ID类型 字典值")
    private String relevanceType;


}