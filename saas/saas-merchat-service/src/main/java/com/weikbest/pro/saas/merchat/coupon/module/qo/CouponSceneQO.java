package com.weikbest.pro.saas.merchat.coupon.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 优惠券使用场景表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponSceneQO对象", description = "优惠券使用场景表")
public class CouponSceneQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    private String couponType;

    @ApiModelProperty("场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取")
    private String couponSceneType;


}