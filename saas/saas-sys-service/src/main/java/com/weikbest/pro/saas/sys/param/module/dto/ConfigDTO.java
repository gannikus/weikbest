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
 * 系统配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ConfigDTO对象", description = "系统配置表")
public class ConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "配置码不为空!")
    @ApiModelProperty(value = "配置码", required = true)
    private String number;

    @NotBlank(message = "配置值不为空!")
    @ApiModelProperty(value = "配置值", required = true)
    private String name;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}