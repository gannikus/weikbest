package com.weikbest.pro.saas.merchat.coupon.module.dto;

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

/**
 * <p>
 * 客户领用优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppCustCouponRestrictVO对象", description = "客户领用优惠券表")
public class AppCustCouponRestrictDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("coupon_id ")
    private Long couponId;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("卡包图标链接")
    private String merchatLogoUrl;

    @ApiModelProperty("卡包图标链接(oss存储)")
    private String merchatLogoOssUrl;

    @ApiModelProperty("用劵结束时间")
    private Date useEndTime;

    @ApiModelProperty("活动结束时间")
    private Date eventEndTime;

    @ApiModelProperty("优惠金额（元）")
    private BigDecimal discountAmount;

    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    private String couponType;

    @ApiModelProperty("使用门槛 0-无使用门槛 1-订单满多少金额可用")
    private String couponUseType;

    @ApiModelProperty("使用门槛价格（元）")
    private BigDecimal couponUsePrice;

    @ApiModelProperty("核销小程序ID")
    private String appId;

    @ApiModelProperty("用券页面链接")
    private String couponUseUrl;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户优惠券领券Id")
    private Long custCouponRestrictId;

}
