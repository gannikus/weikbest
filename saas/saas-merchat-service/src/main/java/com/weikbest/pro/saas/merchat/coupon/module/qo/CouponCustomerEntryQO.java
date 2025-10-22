package com.weikbest.pro.saas.merchat.coupon.module.qo;

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
 * 优惠券与适用客户拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponCustomerEntryQO对象", description = "优惠券与适用客户拆分表")
public class CouponCustomerEntryQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("店铺优惠券类型 1-立减券 2-回流劵 3-平台券")
    private String couponType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("领取人手机号")
    private String restrictUserPhone;


}