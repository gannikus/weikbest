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
@ApiModel(value = "LogQO对象", description = "系统日志表")
public class LogQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作用户ID")
    private Long userId;

    @ApiModelProperty("操作流水号")
    private String traceId;

    @ApiModelProperty("操作类名")
    private String className;

    @ApiModelProperty("操作方法名")
    private String methodName;

    @ApiModelProperty("操作时间 yyyy-mm-dd hh:mm:ss")
    private Date operateTime;

    @ApiModelProperty("操作详情")
    private String details;

    @ApiModelProperty("操作IP")
    private String operateIp;

    @ApiModelProperty("日志类型 A-授权 Q-查询 R-删除 S-新增 U-更新 SU-新增或更新")
    private String logType;

    @ApiModelProperty("操作来源 0-平台端 1-商家端 2-小程序")
    private String recordSource;


}