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
 * 优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppCouponDetailVO对象", description = "优惠券表")
public class AppCouponDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("优惠券内部备注")
    private String tips;

    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    private String couponType;

    @ApiModelProperty("活动结束时间")
    private Date eventEndTime;

    @ApiModelProperty("使用门槛 0-无使用门槛 1-订单满多少金额可用")
    private String couponUseType;

    @ApiModelProperty("使用门槛价格（元）")
    private BigDecimal couponUsePrice;

    @ApiModelProperty("优惠金额（元）")
    private BigDecimal discountAmount;

    @ApiModelProperty("领劵结束时间")
    private Date getEndTime;

    @ApiModelProperty("备注")
    private String description;

}