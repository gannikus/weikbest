package com.weikbest.pro.saas.merchat.shop.module.vo;

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
@ApiModel(value = "ShopUserRoleVO对象", description = "店铺用户角色关联表")
public class ShopUserRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺用户ID")
    private Long shopUserId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("角色ID")
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户账户ID")
    private Long businessUserId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("是否选中 0-否 1-是")
    private Boolean checked;


}