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
@ApiModel(value = "SmsRecordDTO对象", description = "短信发送记录表")
public class SmsRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "短信模板ID不为空!")
    @ApiModelProperty(value = "短信模板ID", required = true)
    private Long smsTemplateId;

    @NotBlank(message = "短信模板编码不为空!")
    @ApiModelProperty(value = "短信模板编码", required = true)
    private String smsNumber;

    @NotBlank(message = "短信模板名称不为空!")
    @ApiModelProperty(value = "短信模板名称", required = true)
    private String smsName;

    @NotBlank(message = "短信模板类型不为空!")
    @ApiModelProperty(value = "短信模板类型", required = true)
    private String smsType;

    @NotBlank(message = "发送内容 不为空!")
    @ApiModelProperty(value = "发送内容 ", required = true)
    private String sendContent;

    @NotBlank(message = "发送参数映射 不为空!")
    @ApiModelProperty(value = "发送参数映射 ", required = true)
    private String sendParam;

    @NotBlank(message = "发送手机号 不为空!")
    @ApiModelProperty(value = "发送手机号 ", required = true)
    private String sendPhone;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "接收用户ID 不为空!")
    @ApiModelProperty(value = "接收用户ID ", required = true)
    private Long receiptUserid;

    @NotBlank(message = "接收用户名称 不为空!")
    @ApiModelProperty(value = "接收用户名称 ", required = true)
    private String receiptUsername;

    @NotBlank(message = "发送状态 不为空!")
    @ApiModelProperty(value = "发送状态 ", required = true)
    private String sendStatus;

    @ApiModelProperty(value = "发送时间  yyyy-MM-dd HH:mm:ss ", required = true)
    private Date sendTime;

    @NotBlank(message = "发送返回报文 不为空!")
    @ApiModelProperty(value = "发送返回报文 ", required = true)
    private String sendResponse;

    @NotBlank(message = "关联ID 不为空!")
    @ApiModelProperty(value = "关联ID ", required = true)
    private String relevanceId;

    @NotBlank(message = "关联ID类型 字典值不为空!")
    @ApiModelProperty(value = "关联ID类型 字典值", required = true)
    private String relevanceType;


}