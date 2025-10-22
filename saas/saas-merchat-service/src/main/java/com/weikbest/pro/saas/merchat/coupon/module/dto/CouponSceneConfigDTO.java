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
@ApiModel(value = "CouponSceneConfigDTO对象", description = "优惠券使用场景配置表")
public class CouponSceneConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取不为空!")
    @ApiModelProperty(value = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取", required = true)
    private String couponSceneType;

    @NotBlank(message = "平台优惠券领取弹窗不为空!")
    @ApiModelProperty(value = "平台优惠券领取弹窗", required = true)
    private String platformCouponReceiveOpenUrl;

    @NotBlank(message = "平台券领取banner图不为空!")
    @ApiModelProperty(value = "平台券领取banner图", required = true)
    private String platformCouponReceiveBannerUrl;


}