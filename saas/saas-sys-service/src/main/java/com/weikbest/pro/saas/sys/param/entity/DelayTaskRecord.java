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
 * 系统延时任务执行记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_delay_task_record")
@ApiModel(value = "DelayTaskRecord对象", description = "系统延时任务执行记录表")
public class DelayTaskRecord implements Serializable {

    public static final String ID = "id";
    public static final String DELAY_TASK_ID = "delay_task_id";
    public static final String DELAY_TASK = "delay_task";
    public static final String NAME = "name";
    public static final String TIMEOUT_DATE = "timeout_date";
    public static final String TASK_STATUS = "task_status";
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
    @ApiModelProperty("延时任务配置ID")
    @TableField("delay_task_id")
    private Long delayTaskId;
    @ApiModelProperty("延时任务key")
    @TableField(value = "delay_task", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String delayTask;
    @ApiModelProperty("延时任务名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("超时时间 yyyy-mm-dd hh:mm:ss")
    @TableField("timeout_date")
    private Date timeoutDate;
    @ApiModelProperty("任务执行状态 -1-已删除 0-等待执行 1-执行成功 2-执行异常 9-无法执行")
    @TableField(value = "task_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String taskStatus;
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
