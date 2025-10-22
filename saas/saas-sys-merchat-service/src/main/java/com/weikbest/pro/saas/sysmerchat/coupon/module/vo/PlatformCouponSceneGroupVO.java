package com.weikbest.pro.saas.sysmerchat.coupon.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 平台优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponSceneGroupVO对象", description = "平台优惠券场景分组对象")
public class PlatformCouponSceneGroupVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取")
    private String couponSceneType;

    @ApiModelProperty("优惠券场景详情集合")
    private List<PlatformCouponSceneDetailVO> platformCouponSceneDetailVOList;

    @ApiModelProperty(value = "平台优惠券领取弹窗")
    private String platformCouponReceiveOpenUrl;

    @ApiModelProperty(value = "平台券领取banner图")
    private String platformCouponReceiveBannerUrl;
}