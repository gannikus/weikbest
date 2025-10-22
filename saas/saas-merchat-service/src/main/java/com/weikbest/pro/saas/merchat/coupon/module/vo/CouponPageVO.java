package com.weikbest.pro.saas.merchat.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponVO对象", description = "优惠券表")
public class CouponPageVO extends CouponVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;

    @ApiModelProperty("已使用 ")
    private Integer useTotal;

    @ApiModelProperty("已领取 ")
    private Integer restrictTotal;

    @ApiModelProperty("店铺名称 ")
    private String shopName;
}