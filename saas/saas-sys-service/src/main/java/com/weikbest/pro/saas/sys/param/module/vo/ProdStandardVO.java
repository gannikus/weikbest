package com.weikbest.pro.saas.sys.param.module.vo;

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
@ApiModel(value = "ProdStandardVO对象", description = "系统商品规范表")
public class ProdStandardVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("支付失败默认弹窗样式")
    private String payFailThemeUrl;

    @ApiModelProperty("左滑弹窗默认样式")
    private String leftSlideThemeUrl;

    @ApiModelProperty("回流优惠券-弹框默认样式图片")
    private String refluxCouponThemeUrl;

    @ApiModelProperty("回流优惠券数量上限")
    private Integer refluxCouponNum;

    @ApiModelProperty("回流优惠券卡包-默认图片")
    private String refluxCouponImageUrl;

    @ApiModelProperty("回流优惠券卡包-默认颜色")
    private String refluxBackgroundColor;

    @ApiModelProperty("立减优惠券卡包-默认图标")
    private String promptlyMerchatLogoUrl;

    @ApiModelProperty("立减优惠券卡包-默认图片")
    private String promptlyCouponImageUrl;

    @ApiModelProperty("立减优惠券卡包-默认颜色")
    private String promptlyBackgroundColor;

    @ApiModelProperty("平台优惠券卡包-默认图标")
    private String platformMerchatLogoUrl;

    @ApiModelProperty("平台优惠券卡包-默认图片")
    private String platformCouponImageUrl;

    @ApiModelProperty("平台优惠券卡包-默认颜色")
    private String platformBackgroundColor;

    @ApiModelProperty("商品售价下限元")
    private Integer prodPriceFloor;

    @ApiModelProperty("微信优惠券核销方式：OFF_LINE：线下滴码核销 MINI_PROGRAMS：线上小程序核销 PAYMENT_CODE：微信支付付款码核销 SELF_CONSUME：用户自助核销")
    private String useMethod;

    @ApiModelProperty("优惠券默认核销小程序path")
    private String miniProgramsPath;

    @ApiModelProperty("微信商品券code模式： WECHATPAY_MODE：系统分配券code。（固定22位纯数字）  MERCHANT_API：商户发放时接口指定券code。  MERCHANT_UPLOAD：商户上传自定义code，发券时系统随机选取上传的券code。")
    private String couponCodeMode;


}