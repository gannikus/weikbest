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
 * 系统日志表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_log")
@ApiModel(value = "Log对象", description = "系统日志表")
public class Log implements Serializable {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TRACE_ID = "trace_id";
    public static final String CLASS_NAME = "class_name";
    public static final String METHOD_NAME = "method_name";
    public static final String OPERATE_TIME = "operate_time";
    public static final String DETAILS = "details";
    public static final String OPERATE_IP = "operate_ip";
    public static final String LOG_TYPE = "log_type";
    public static final String RECORD_SOURCE = "record_source";
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
    @ApiModelProperty("操作用户ID")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("操作流水号")
    @TableField(value = "trace_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String traceId;
    @ApiModelProperty("操作类名")
    @TableField(value = "class_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String className;
    @ApiModelProperty("操作方法名")
    @TableField(value = "method_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String methodName;
    @ApiModelProperty("操作时间 yyyy-mm-dd hh:mm:ss")
    @TableField("operate_time")
    private Date operateTime;
    @ApiModelProperty("操作详情")
    @TableField(value = "details", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String details;
    @ApiModelProperty("操作IP")
    @TableField(value = "operate_ip", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String operateIp;
    @ApiModelProperty("日志类型 A-授权 Q-查询 R-删除 S-新增 U-更新 SU-新增或更新")
    @TableField(value = "log_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String logType;
    @ApiModelProperty("操作来源 0-平台端 1-商家端 2-小程序")
    @TableField(value = "record_source", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String recordSource;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
