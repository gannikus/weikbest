package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统商品规范表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-08
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdStandardDTO对象", description = "系统商品规范表")
public class ProdStandardDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付失败默认弹窗样式")
    private String payFailThemeUrl;

    @ApiModelProperty(value = "左滑弹窗默认样式")
    private String leftSlideThemeUrl;

    @ApiModelProperty(value = "回流优惠券-弹框默认样式图片")
    private String refluxCouponThemeUrl;

    @ApiModelProperty(value = "回流优惠券数量上限")
    private Integer refluxCouponNum;

    @ApiModelProperty(value = "回流优惠券卡包-默认图片(oss存储)")
    private String refluxCouponImageOssurl;

    @ApiModelProperty(value = "立减优惠券卡包-默认图标(oss存储)")
    private String promptlyMerchatLogoOssurl;

    @ApiModelProperty(value = "立减优惠券卡包-默认图片(oss存储)")
    private String promptlyCouponImageOssurl;

    @ApiModelProperty(value = "平台优惠券卡包-默认图标(oss存储)")
    private String platformMerchatLogoOssurl;

    @ApiModelProperty(value = "平台优惠券卡包-默认图片(oss存储)")
    private String platformCouponImageOssurl;

    @ApiModelProperty(value = "回流优惠券卡包-默认图片")
    private String refluxCouponImageUrl;

    @ApiModelProperty(value = "立减优惠券卡包-默认图标")
    private String promptlyMerchatLogoUrl;

    @ApiModelProperty(value = "立减优惠券卡包-默认图片")
    private String promptlyCouponImageUrl;

    @ApiModelProperty(value = "平台优惠券卡包-默认图标")
    private String platformMerchatLogoUrl;

    @ApiModelProperty(value = "平台优惠券卡包-默认图片")
    private String platformCouponImageUrl;

    @ApiModelProperty(value = "商品售价下限元")
    private Integer prodPriceFloor;

}