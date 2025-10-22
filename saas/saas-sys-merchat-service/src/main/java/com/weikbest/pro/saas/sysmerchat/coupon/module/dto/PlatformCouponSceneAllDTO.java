package com.weikbest.pro.saas.sysmerchat.coupon.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 平台优惠券使用场景表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponSceneAllDTO对象", description = "平台优惠券使用场景对象")
public class PlatformCouponSceneAllDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告进入绑定回流优惠券ID")
    private List<Long> platformCouponId1List;

    @ApiModelProperty(value = "新用户进入小程序点击ID")
    private List<Long> platformCouponId2List;

    @ApiModelProperty(value = "平台优惠券领取弹窗")
    private String platformCouponReceiveOpenUrl;

    @ApiModelProperty(value = "平台券领取banner图")
    private String platformCouponReceiveBannerUrl;


}