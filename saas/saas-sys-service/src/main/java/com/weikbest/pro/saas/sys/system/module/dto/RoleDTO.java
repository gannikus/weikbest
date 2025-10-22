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
 * 系统角色表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RoleDTO对象", description = "系统角色表")
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "名称不为空!")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @NotBlank(message = "角色类型不为空!")
    @ApiModelProperty(value = "角色类型 0-平台角色 1-商家端角色 2-APP端角色", required = true)
    private String roleType;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

}