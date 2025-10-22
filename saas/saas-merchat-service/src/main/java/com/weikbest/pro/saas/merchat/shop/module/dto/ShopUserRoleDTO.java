package com.weikbest.pro.saas.merchat.shop.module.dto;

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
 * 店铺用户角色关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopUserRoleDTO对象", description = "店铺用户角色关联表")
public class ShopUserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "店铺用户ID不为空!")
    @ApiModelProperty(value = "店铺用户ID", required = true)
    private Long shopUserId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "角色ID不为空!")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户账户ID不为空!")
    @ApiModelProperty(value = "商户账户ID", required = true)
    private Long businessUserId;


}