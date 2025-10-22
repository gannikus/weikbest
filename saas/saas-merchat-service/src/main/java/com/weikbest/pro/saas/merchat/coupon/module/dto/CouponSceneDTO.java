package com.weikbest.pro.saas.merchat.coupon.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "CouponSceneDTO对象", description = "优惠券使用场景表")
public class CouponSceneDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "优惠券类型 不为空!")
    @ApiModelProperty(value = "优惠券类型 1-立减券 2-回流劵 3-平台券", required = true)
    private String couponType;

    @NotBlank(message = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取不为空!")
    @ApiModelProperty(value = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取", required = true)
    private String couponSceneType;


}