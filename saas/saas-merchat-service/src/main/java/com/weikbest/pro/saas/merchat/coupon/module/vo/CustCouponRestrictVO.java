package com.weikbest.pro.saas.merchat.coupon.module.vo;

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
@ApiModel(value = "CustCouponRestrictVO对象", description = "客户领用优惠券表")
public class CustCouponRestrictVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("优惠券ID")
    private Long couponId;

    @ApiModelProperty("优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券")
    private String couponType;

    @ApiModelProperty("优惠券领用状态 1-未生效 5-未使用 10-已过期 15-冻结中 20-用券核销 25-主动核销")
    private String restrictType;

    @ApiModelProperty("领取人手机号")
    private String restrictUserPhone;

    @ApiModelProperty("领用日期 yyyy-MM-dd HH:mm:ss")
    private Date restrictDate;

    @ApiModelProperty("领用使用日期 yyyy-MM-dd HH:mm:ss")
    private Date restrictUseDate;

    @ApiModelProperty("微信优惠券唯一标识")
    private String couponCode;

    @ApiModelProperty("微信优惠券核销凭据号")
    private String outRequestNo;

    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    private String adAid;



}