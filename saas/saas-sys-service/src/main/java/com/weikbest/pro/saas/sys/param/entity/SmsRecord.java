package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("t_sys_sms_record")
@ApiModel(value = "SmsRecord对象", description = "短信发送记录表")
public class SmsRecord implements Serializable {

    public static final String ID = "id";
    public static final String SMS_TEMPLATE_ID = "sms_template_id";
    public static final String SMS_NUMBER = "sms_number";
    public static final String SMS_NAME = "sms_name";
    public static final String SMS_TYPE = "sms_type";
    public static final String SEND_CONTENT = "send_content";
    public static final String SEND_PARAM = "send_param";
    public static final String SEND_PHONE = "send_phone";
    public static final String RECEIPT_USERID = "receipt_userid";
    public static final String RECEIPT_USERNAME = "receipt_username";
    public static final String SEND_STATUS = "send_status";
    public static final String SEND_TIME = "send_time";
    public static final String SEND_RESPONSE = "send_response";
    public static final String RELEVANCE_ID = "relevance_id";
    public static final String RELEVANCE_TYPE = "relevance_type";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("短信模板ID")
    @TableField("sms_template_id")
    private Long smsTemplateId;
    @ApiModelProperty("短信模板编码")
    @TableField(value = "sms_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String smsNumber;
    @ApiModelProperty("短信模板名称")
    @TableField(value = "sms_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String smsName;
    @ApiModelProperty("短信模板类型")
    @TableField(value = "sms_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String smsType;
    @ApiModelProperty("发送内容 ")
    @TableField(value = "send_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendContent;
    @ApiModelProperty("发送参数映射 ")
    @TableField(value = "send_param", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendParam;
    @ApiModelProperty("发送手机号 ")
    @TableField(value = "send_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendPhone;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("接收用户ID ")
    @TableField("receipt_userid")
    private Long receiptUserid;
    @ApiModelProperty("接收用户名称 ")
    @TableField(value = "receipt_username", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiptUsername;
    @ApiModelProperty("发送状态 ")
    @TableField(value = "send_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendStatus;
    @ApiModelProperty("发送时间  yyyy-MM-dd HH:mm:ss ")
    @TableField("send_time")
    private Date sendTime;
    @ApiModelProperty("发送返回报文 ")
    @TableField(value = "send_response", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendResponse;
    @ApiModelProperty("关联ID ")
    @TableField(value = "relevance_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String relevanceId;
    @ApiModelProperty("关联ID类型 字典值")
    @TableField(value = "relevance_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String relevanceType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField("modifier")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
