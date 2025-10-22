package com.weikbest.pro.saas.applet.coupon.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppCouponSceneVO对象", description = "店铺优惠券表")
public class AppCouponSceneVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("平台优惠券领取弹窗")
    private String platformCouponReceiveOpenUrl;

    @ApiModelProperty("平台券领取banner图")
    private String platformCouponReceiveBannerUrl;

    @ApiModelProperty("平台优惠券信息")
    private Map map;

    @ApiModelProperty("客户领券信息")
    private List<AppCustCouponRestrictVO> appCustCouponRestrictVOS;
}