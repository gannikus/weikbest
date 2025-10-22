package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 系统延时任务表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DelayTaskDTO对象", description = "系统延时任务表")
public class DelayTaskConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "延时任务编码不为空!")
    @ApiModelProperty(value = "延时任务编码", required = true)
    private String number;

    @NotBlank(message = "延时任务名称不为空!")
    @ApiModelProperty(value = "延时任务名称", required = true)
    private String name;


}