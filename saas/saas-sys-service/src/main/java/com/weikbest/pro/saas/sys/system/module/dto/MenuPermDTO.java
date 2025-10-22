package com.weikbest.pro.saas.sys.system.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统菜单权限项关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "MenuPermDTO对象", description = "系统菜单权限项关联表")
public class MenuPermDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "菜单ID不为空!")
    @ApiModelProperty(value = "菜单ID", required = true)
    private Long menuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "权限项ID不为空!")
    @ApiModelProperty(value = "权限项ID", required = true)
    private Long permId;


}