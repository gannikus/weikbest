package com.weikbest.pro.saas.merchat.shop.module.qo;

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
 * 店铺角色表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopRoleQO对象", description = "店铺角色表")
public class ShopRoleQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long shopId;

    @ApiModelProperty("角色名称")
    private String name;

}