package com.weikbest.pro.saas.applet.coupon.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class AppCustCouponRestrictVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

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

    @ApiModelProperty("微信优惠券唯一标识")
    private String couponCode;

    @ApiModelProperty("领券请求单号")
    private String getRequestNo;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @ApiModelProperty("关联小程序appid")
    private String appId;

    @ApiModelProperty("是否授权平台 0-否 1-是")
    private String isTerrace;

    @ApiModelProperty("平台wxPayMchId")
    private String terraceWxPayMchId;

    @ApiModelProperty("平台v2key")
    private String terraceWxPayMchKey;

}