package com.weikbest.pro.saas.merchat.coupon.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 优惠券使用场景配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-15
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponSceneConfigVO对象", description = "优惠券使用场景配置表")
public class CouponSceneConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取")
    private String couponSceneType;

    @ApiModelProperty("平台优惠券领取弹窗")
    private String platformCouponReceiveOpenUrl;

    @ApiModelProperty("平台券领取banner图")
    private String platformCouponReceiveBannerUrl;


}