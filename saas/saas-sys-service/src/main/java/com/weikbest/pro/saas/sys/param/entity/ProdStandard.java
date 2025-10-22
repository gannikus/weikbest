package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("t_sys_prod_standard")
@ApiModel(value = "ProdStandard对象", description = "系统商品规范表")
public class ProdStandard implements Serializable {

    public static final String ID = "id";
    public static final String PAY_FAIL_THEME_URL = "pay_fail_theme_url";
    public static final String LEFT_SLIDE_THEME_URL = "left_slide_theme_url";
    public static final String REFLUX_COUPON_THEME_URL = "reflux_coupon_theme_url";
    public static final String REFLUX_COUPON_NUM = "reflux_coupon_num";
    public static final String REFLUX_COUPON_IMAGE_URL = "reflux_coupon_image_url";
    public static final String REFLUX_COUPON_IMAGE_OSSURL = "reflux_coupon_image_ossurl";
    public static final String REFLUX_BACKGROUND_COLOR = "reflux_background_color";
    public static final String PROMPTLY_MERCHAT_LOGO_URL = "promptly_merchat_logo_url";
    public static final String PROMPTLY_MERCHAT_LOGO_OSSURL = "promptly_merchat_logo_ossurl";
    public static final String PROMPTLY_COUPON_IMAGE_URL = "promptly_coupon_image_url";
    public static final String PROMPTLY_COUPON_IMAGE_OSSURL = "promptly_coupon_image_ossurl";
    public static final String PROMPTLY_BACKGROUND_COLOR = "promptly_background_color";
    public static final String PLATFORM_MERCHAT_LOGO_URL = "platform_merchat_logo_url";
    public static final String PLATFORM_MERCHAT_LOGO_OSSURL = "platform_merchat_logo_ossurl";
    public static final String PLATFORM_COUPON_IMAGE_URL = "platform_coupon_image_url";
    public static final String PLATFORM_COUPON_IMAGE_OSSURL = "platform_coupon_image_ossurl";
    public static final String PLATFORM_BACKGROUND_COLOR = "platform_background_color";
    public static final String PROD_PRICE_FLOOR = "prod_price_floor";
    public static final String USE_METHOD = "use_method";
    public static final String MINI_PROGRAMS_PATH = "mini_programs_path";
    public static final String COUPON_CODE_MODE = "coupon_code_mode";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("支付失败默认弹窗样式")
    @TableField(value = "pay_fail_theme_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payFailThemeUrl;
    @ApiModelProperty("左滑弹窗默认样式")
    @TableField(value = "left_slide_theme_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String leftSlideThemeUrl;
    @ApiModelProperty("回流优惠券-弹框默认样式图片")
    @TableField(value = "reflux_coupon_theme_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refluxCouponThemeUrl;
    @ApiModelProperty("回流优惠券数量上限")
    @TableField("reflux_coupon_num")
    private Integer refluxCouponNum;
    @ApiModelProperty("回流优惠券卡包-默认图片")
    @TableField(value = "reflux_coupon_image_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refluxCouponImageUrl;
    @ApiModelProperty("回流优惠券卡包-默认图片(oss存储)")
    @TableField(value = "reflux_coupon_image_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refluxCouponImageOssurl;
    @ApiModelProperty("回流优惠券卡包-默认颜色")
    @TableField(value = "reflux_background_color", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refluxBackgroundColor;
    @ApiModelProperty("立减优惠券卡包-默认图标")
    @TableField(value = "promptly_merchat_logo_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String promptlyMerchatLogoUrl;
    @ApiModelProperty("立减优惠券卡包-默认图标(oss存储)")
    @TableField(value = "promptly_merchat_logo_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String promptlyMerchatLogoOssurl;
    @ApiModelProperty("立减优惠券卡包-默认图片")
    @TableField(value = "promptly_coupon_image_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String promptlyCouponImageUrl;
    @ApiModelProperty("立减优惠券卡包-默认图片(oss存储)")
    @TableField(value = "promptly_coupon_image_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String promptlyCouponImageOssurl;
    @ApiModelProperty("立减优惠券卡包-默认颜色")
    @TableField(value = "promptly_background_color", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String promptlyBackgroundColor;
    @ApiModelProperty("平台优惠券卡包-默认图标")
    @TableField(value = "platform_merchat_logo_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformMerchatLogoUrl;
    @ApiModelProperty("平台优惠券卡包-默认图标(oss存储)")
    @TableField(value = "platform_merchat_logo_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformMerchatLogoOssurl;
    @ApiModelProperty("平台优惠券卡包-默认图片")
    @TableField(value = "platform_coupon_image_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformCouponImageUrl;
    @ApiModelProperty("平台优惠券卡包-默认图片(oss存储)")
    @TableField(value = "platform_coupon_image_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformCouponImageOssurl;
    @ApiModelProperty("平台优惠券卡包-默认颜色")
    @TableField(value = "platform_background_color", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformBackgroundColor;
    @ApiModelProperty("商品售价下限元")
    @TableField("prod_price_floor")
    private Integer prodPriceFloor;
    @ApiModelProperty("微信优惠券核销方式：OFF_LINE：线下滴码核销 MINI_PROGRAMS：线上小程序核销 PAYMENT_CODE：微信支付付款码核销 SELF_CONSUME：用户自助核销")
    @TableField(value = "use_method", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String useMethod;
    @ApiModelProperty("优惠券默认核销小程序path")
    @TableField(value = "mini_programs_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String miniProgramsPath;
    @ApiModelProperty("微信商品券code模式： WECHATPAY_MODE：系统分配券code。（固定22位纯数字）  MERCHANT_API：商户发放时接口指定券code。  MERCHANT_UPLOAD：商户上传自定义code，发券时系统随机选取上传的券code。")
    @TableField(value = "coupon_code_mode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponCodeMode;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
