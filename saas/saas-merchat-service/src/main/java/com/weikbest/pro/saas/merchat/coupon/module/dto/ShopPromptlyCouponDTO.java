package com.weikbest.pro.saas.merchat.coupon.module.dto;

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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@ApiModel(value = "ShopPromptlyCouponDTO对象", description = "店铺立减优惠券")
public class ShopPromptlyCouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @NotBlank(message = "优惠券名称不为空!")
    @ApiModelProperty(value = "优惠券名称", required = true)
    private String name;

    @ApiModelProperty(value = "优惠券内部备注")
    private String tips;

    @NotNull(message = "活动开始时间不为空!")
    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date eventStartTime;

    @NotNull(message = "活动结束时间不为空!")
    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date eventEndTime;

    @NotNull(message = "优惠券发行总量不为空!")
    @ApiModelProperty(value = "优惠券发行总量", required = true)
    private Integer couponNum;

    @NotBlank(message = "适用商品类型 不为空!")
    @ApiModelProperty(value = "适用商品类型 1-全部商品 2-部分商品", required = true)
    private String couponProdType;

    @ApiModelProperty(value = "适用商品ID集合")
    private List<Long> prodIdList;

    @NotBlank(message = "使用门槛 不为空!")
    @ApiModelProperty(value = "使用门槛 0-无使用门槛 1-订单满多少金额可用", required = true)
    private String couponUseType;

    @NotNull(message = "使用门槛价格（元）不为空!")
    @ApiModelProperty(value = "使用门槛价格（元）", required = true)
    private BigDecimal couponUsePrice;

    @NotNull(message = "优惠金额（元）不为空!")
    @ApiModelProperty(value = "优惠金额（元）", required = true)
    private BigDecimal discountAmount;

    @NotBlank(message = "核销小程序ID不为空!")
    @ApiModelProperty(value = "核销小程序ID", required = true)
    private String appId;

    @NotNull(message = "领劵开始时间不为空!")
    @ApiModelProperty(value = "领劵开始时间", required = true)
    private Date getStartTime;

    @NotNull(message = "领劵结束时间不为空!")
    @ApiModelProperty(value = "领劵结束时间", required = true)
    private Date getEndTime;

    @NotNull(message = "每人限领次数不为空!")
    @ApiModelProperty(value = "每人限领次数", required = true)
    private Integer restrictCount;

    @NotBlank(message = "领取人限制类型 不为空!")
    @ApiModelProperty(value = "领取人限制类型 1-不限制，所有人可领取 2-指定客户领取", required = true)
    private String restrictUserType;

    @ApiModelProperty(value = "指定客户手机号集合")
    private List<String> customerPhoneList;

    @NotBlank(message = "是否公开 不为空!")
    @ApiModelProperty(value = "是否公开 0-否 1-是", required = true)
    private String isOpen;

    @NotNull(message = "领券（天）后生效不为空!")
    @ApiModelProperty(value = "领券（天）后生效", required = true)
    private Integer delayEnableDay;

    @NotNull(message = "有效期（天）不为空!")
    @ApiModelProperty(value = "有效期（天）", required = true)
    private Integer validityDay;

    @ApiModelProperty(value = "卡包详情图片链接(oss存储)")
    private String couponImageOssurl;

    @ApiModelProperty(value = "卡包详情图片链接")
    private String couponImageUrl;

}