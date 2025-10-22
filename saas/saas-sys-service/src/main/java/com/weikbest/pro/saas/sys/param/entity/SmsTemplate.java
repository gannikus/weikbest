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
@TableName("t_sys_sms_template")
@ApiModel(value = "SmsTemplate对象", description = "短信模板表")
public class SmsTemplate implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String SMS_TYPE = "sms_type";
    public static final String CONTENT = "content";
    public static final String PARAMS = "params";
    public static final String BIND_URL = "bind_url";
    public static final String IS_PRESET = "is_preset";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("短信模板编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("短信模板名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("短信模板类型")
    @TableField(value = "sms_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String smsType;
    @ApiModelProperty("短信模板内容")
    @TableField(value = "content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String content;
    @ApiModelProperty("短信模板参数详情")
    @TableField(value = "params", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String params;
    @ApiModelProperty("短信绑定路径")
    @TableField(value = "bind_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindUrl;
    @ApiModelProperty("平台预置 0-否 1-是")
    @TableField(value = "is_preset", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isPreset;
    @ApiModelProperty("描述")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
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
