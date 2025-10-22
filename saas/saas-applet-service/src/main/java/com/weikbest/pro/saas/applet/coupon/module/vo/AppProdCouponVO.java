package com.weikbest.pro.saas.applet.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@ApiModel(value = "ShopCouponVO对象", description = "店铺优惠券表")
public class AppProdCouponVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否设置优惠券 0-否 1-是")
    private String isOpenCoupon;

    @ApiModelProperty("主动核销 0-不主动 1-主动")
    private String chargeOff;

    @ApiModelProperty("领劵弹窗图片")
    private String couponOpenImg;

    @ApiModelProperty("优惠券信息")
    private Map map;

    @ApiModelProperty("客户领券信息")
    private List<AppCustCouponRestrictVO> appCustCouponRestrictVOS;

    @ApiModelProperty("设置立减券优惠券信息")
    private List<AppCouponDetailVO> detailVOS;
}