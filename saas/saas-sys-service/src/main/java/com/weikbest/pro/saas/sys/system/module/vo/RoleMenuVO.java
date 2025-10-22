package com.weikbest.pro.saas.sys.system.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统角色菜单关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RoleMenuVO对象", description = "系统角色菜单关联表")
public class RoleMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("角色ID")
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "模块ID")
    private Long menuModuleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "权限项ID")
    private Long permId;


}