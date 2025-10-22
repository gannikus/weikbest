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
@ApiModel(value = "DelayTaskRecordDTO对象", description = "系统延时任务执行记录表")
public class DelayTaskRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "延时任务配置ID不为空!")
    @ApiModelProperty(value = "延时任务配置ID", required = true)
    private Long delayTaskId;

    @NotBlank(message = "延时任务key不为空!")
    @ApiModelProperty(value = "延时任务key", required = true)
    private String delayTask;

    @NotBlank(message = "延时任务名称不为空!")
    @ApiModelProperty(value = "延时任务名称", required = true)
    private String name;

    @ApiModelProperty(value = "超时时间 yyyy-mm-dd hh:mm:ss", required = true)
    private Date timeoutDate;

    @NotBlank(message = "任务执行状态 -1-已删除 0-等待执行 1-执行成功 2-执行异常 9-无法执行不为空!")
    @ApiModelProperty(value = "任务执行状态 -1-已删除 0-等待执行 1-执行成功 2-执行异常 9-无法执行", required = true)
    private String taskStatus;


}