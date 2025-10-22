package com.weikbest.pro.saas.merchat.cust.module.vo;

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
 * 客户关注店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustShopAttentVO对象", description = "客户关注店铺表")
public class CustShopAttentVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    private Long customerId;


}