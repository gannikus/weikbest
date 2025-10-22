package com.weikbest.pro.saas.merchat.coupon.module.vo;

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
 * 优惠券与适用商品拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponProdEntryVO对象", description = "优惠券与适用商品拆分表")
public class CouponProdEntryVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联优惠券ID ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    private Long prodId;

    @ApiModelProperty("店铺优惠券类型 1-立减券 2-回流劵")
    private String shopCouponType;


}