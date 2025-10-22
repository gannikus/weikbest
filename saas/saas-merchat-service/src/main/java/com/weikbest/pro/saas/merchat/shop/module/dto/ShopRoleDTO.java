package com.weikbest.pro.saas.merchat.shop.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "ShopRoleDTO对象", description = "店铺角色表")
public class ShopRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "店铺ID不为空!")
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @NotBlank(message = "名称不为空!")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty("描述")
    private String description;

}