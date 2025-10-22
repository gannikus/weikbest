package com.weikbest.pro.saas.merchat.coupon.module.dto;

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
@ApiModel(value = "CouponProdEntryDTO对象", description = "优惠券与适用商品拆分表")
public class CouponProdEntryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID 不为空!")
    @ApiModelProperty(value = "关联商品ID ", required = true)
    private Long prodId;

    @NotBlank(message = "店铺优惠券类型 不为空!")
    @ApiModelProperty(value = "店铺优惠券类型 1-立减券 2-回流劵 3-平台券", required = true)
    private String couponType;

}