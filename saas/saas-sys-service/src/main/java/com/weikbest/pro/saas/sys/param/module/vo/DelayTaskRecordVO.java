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
@ApiModel(value = "DelayTaskRecordVO对象", description = "系统延时任务执行记录表")
public class DelayTaskRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("延时任务配置ID")
    private Long delayTaskId;

    @ApiModelProperty("延时任务key")
    private String delayTask;

    @ApiModelProperty("延时任务名称")
    private String name;

    @ApiModelProperty("超时时间 yyyy-mm-dd hh:mm:ss")
    private Date timeoutDate;

    @ApiModelProperty("任务执行状态 -1-已删除 0-等待执行 1-执行成功 2-执行异常 9-无法执行")
    private String taskStatus;


}