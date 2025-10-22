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
@ApiModel(value = "LogDTO对象", description = "系统日志表")
public class LogDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "操作用户ID不为空!")
    @ApiModelProperty(value = "操作用户ID", required = true)
    private Long userId;

    @NotBlank(message = "操作流水号不为空!")
    @ApiModelProperty(value = "操作流水号", required = true)
    private String traceId;

    @NotBlank(message = "操作类名不为空!")
    @ApiModelProperty(value = "操作类名", required = true)
    private String className;

    @NotBlank(message = "操作方法名不为空!")
    @ApiModelProperty(value = "操作方法名", required = true)
    private String methodName;

    @ApiModelProperty(value = "操作时间 yyyy-mm-dd hh:mm:ss", required = true)
    private Date operateTime;

    @NotBlank(message = "操作详情不为空!")
    @ApiModelProperty(value = "操作详情", required = true)
    private String details;

    @NotBlank(message = "操作IP不为空!")
    @ApiModelProperty(value = "操作IP", required = true)
    private String operateIp;

    @NotBlank(message = "日志类型 A-授权 Q-查询 R-删除 S-新增 U-更新 SU-新增或更新不为空!")
    @ApiModelProperty(value = "日志类型 A-授权 Q-查询 R-删除 S-新增 U-更新 SU-新增或更新", required = true)
    private String logType;

    @NotBlank(message = "操作来源 0-平台端 1-商家端 2-小程序不为空!")
    @ApiModelProperty(value = "操作来源 0-平台端 1-商家端 2-小程序", required = true)
    private String recordSource;


}