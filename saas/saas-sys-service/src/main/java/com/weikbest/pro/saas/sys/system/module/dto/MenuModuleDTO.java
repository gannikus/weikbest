package com.weikbest.pro.saas.sys.system.module.dto;

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
 * 系统模块表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "MenuModuleDTO对象", description = "系统模块表")
public class MenuModuleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "模块编码不为空!")
    @ApiModelProperty(value = "模块编码", required = true)
    private String number;

    @NotBlank(message = "模块名称不为空!")
    @ApiModelProperty(value = "模块名称", required = true)
    private String name;

    @ApiModelProperty(value = "模块排序", required = true)
    private Integer moduleOrd;

    @NotBlank(message = "模块类型不为空!")
    @ApiModelProperty(value = "模块类型 0-平台模块 1-商家端模块 2-APP端模块", required = true)
    private String moduleType;

    @ApiModelProperty(value = "图标")
    private String iconClass;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}