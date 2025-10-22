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
 * 系统用户角色关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserRoleDTO对象", description = "系统用户角色关联表")
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "用户ID不为空!")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "角色ID不为空!")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;


}