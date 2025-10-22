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
 * 商户店铺用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopUserQO对象", description = "商户店铺用户表")
public class ShopUserQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户账户ID")
    private Long businessUserId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

}