package com.weikbest.pro.saas.merchat.coupon.module.dto;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "CustCouponRestrictDTO对象", description = "客户领用优惠券表")
public class CustCouponRestrictDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "优惠券ID不为空!")
    @ApiModelProperty(value = "优惠券ID", required = true)
    private Long couponId;

    @NotBlank(message = "优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券不为空!")
    @ApiModelProperty(value = "优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券", required = true)
    private String couponType;

    @NotBlank(message = "优惠券领用状态 1-未生效 5-未使用 10-已过期 15-冻结中 20-用券核销 25-主动核销不为空!")
    @ApiModelProperty(value = "优惠券领用状态 1-未生效 5-未使用 10-已过期 15-冻结中 20-用券核销 25-主动核销", required = true)
    private String restrictType;

    @NotBlank(message = "领取人手机号不为空!")
    @ApiModelProperty(value = "领取人手机号", required = true)
    private String restrictUserPhone;

    @ApiModelProperty(value = "领用日期 yyyy-MM-dd HH:mm:ss", required = true)
    private Date restrictDate;

    @ApiModelProperty(value = "领用使用日期 yyyy-MM-dd HH:mm:ss", required = true)
    private Date restrictUseDate;

    @ApiModelProperty("微信优惠券唯一标识")
    private String couponCode;

    @ApiModelProperty("微信优惠券核销凭据号")
    private String outRequestNo;

    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    private String adAid;

}