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
public class AppCouponVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("店铺优惠券类型 1-立减券 2-回流劵")
    private String shopCouponType;

    @ApiModelProperty("优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消")
    private String couponStatus;

    @ApiModelProperty("活动开始时间")
    private Date eventStartTime;

    @ApiModelProperty("活动结束时间")
    private Date eventEndTime;

    @ApiModelProperty("使用门槛 0-无使用门槛 1-订单满多少金额可用")
    private String couponUseType;

    @ApiModelProperty("使用门槛价格（元）")
    private BigDecimal couponUsePrice;

    @ApiModelProperty("优惠金额（元）")
    private BigDecimal discountAmount;

    @ApiModelProperty("领劵开始时间")
    private Date getStartTime;

    @ApiModelProperty("领劵结束时间")
    private Date getEndTime;

    @ApiModelProperty("用劵开始时间")
    private Date useStartTime;

    @ApiModelProperty("用劵结束时间")
    private Date useEndTime;

    @ApiModelProperty("商户创建批次凭据号")
    private String outRequestNo;

    @ApiModelProperty("微信优惠券批次号")
    private String stockId;

    @ApiModelProperty("微信支付商户号")
    private String wxPayMchId;
}