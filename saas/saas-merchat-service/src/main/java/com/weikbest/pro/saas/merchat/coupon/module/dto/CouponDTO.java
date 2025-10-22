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
@ApiModel(value = "CouponDTO对象", description = "优惠券表")
public class CouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @NotBlank(message = "优惠券名称不为空!")
    @ApiModelProperty(value = "优惠券名称", required = true)
    private String name;

    @NotBlank(message = "优惠券内部备注不为空!")
    @ApiModelProperty(value = "优惠券内部备注", required = true)
    private String tips;

    @NotBlank(message = "优惠券类型 1-立减券 2-回流劵 3-平台券不为空!")
    @ApiModelProperty(value = "优惠券类型 1-立减券 2-回流劵 3-平台券", required = true)
    private String couponType;

    @NotBlank(message = "优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消不为空!")
    @ApiModelProperty(value = "优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消", required = true)
    private String couponStatus;

    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date eventStartTime;

    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date eventEndTime;

    @ApiModelProperty(value = "优惠券发行总量", required = true)
    private Integer couponNum;

    @NotBlank(message = "适用商品类型 1-全部商品 2-部分商品不为空!")
    @ApiModelProperty(value = "适用商品类型 1-全部商品 2-部分商品", required = true)
    private String couponProdType;

    @NotBlank(message = "使用门槛 0-无使用门槛 1-订单满多少金额可用不为空!")
    @ApiModelProperty(value = "使用门槛 0-无使用门槛 1-订单满多少金额可用", required = true)
    private String couponUseType;

    @ApiModelProperty(value = "使用门槛价格（元）", required = true)
    private BigDecimal couponUsePrice;

    @NotBlank(message = "核销小程序ID不为空!")
    @ApiModelProperty(value = "核销小程序ID", required = true)
    private String appId;

    @ApiModelProperty(value = "优惠金额（元）", required = true)
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "领劵开始时间", required = true)
    private Date getStartTime;

    @ApiModelProperty(value = "领劵结束时间", required = true)
    private Date getEndTime;

    @ApiModelProperty(value = "每人限领次数", required = true)
    private Integer restrictCount;

    @NotBlank(message = "领取人限制类型 1-不限制，所有人可领取 2-指定客户领取不为空!")
    @ApiModelProperty(value = "领取人限制类型 1-不限制，所有人可领取 2-指定客户领取", required = true)
    private String restrictUserType;

    @NotBlank(message = "是否公开 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否公开 0-否 1-是", required = true)
    private String isOpen;

    @ApiModelProperty(value = "用劵开始时间", required = true)
    private Date useStartTime;

    @ApiModelProperty(value = "用劵结束时间", required = true)
    private Date useEndTime;

    @NotBlank(message = "生效类型 1-领券后立即生效 2-延迟生效不为空!")
    @ApiModelProperty(value = "生效类型 1-领券后立即生效 2-延迟生效", required = true)
    private String enableType;

    @ApiModelProperty(value = "领券（天）后生效", required = true)
    private Integer delayEnableDay;

    @ApiModelProperty(value = "有效期（天）", required = true)
    private Integer validityDay;

    @NotBlank(message = "用券页面链接不为空!")
    @ApiModelProperty(value = "用券页面链接", required = true)
    private String couponUseUrl;

    @NotBlank(message = "优惠券样式 1-默认图片 2-上传图片不为空!")
    @ApiModelProperty(value = "优惠券样式 1-默认图片 2-上传图片", required = true)
    private String couponThemeType;

    @NotBlank(message = "卡包详情图片链接不为空!")
    @ApiModelProperty(value = "卡包详情图片链接", required = true)
    private String couponImageUrl;

    @NotBlank(message = "卡包图标链接不为空!")
    @ApiModelProperty(value = "卡包图标链接", required = true)
    private String merchatLogoUrl;

    @NotBlank(message = "商户创建批次凭据号不为空!")
    @ApiModelProperty(value = "商户创建批次凭据号", required = true)
    private String outRequestNo;

    @NotBlank(message = "微信优惠券批次号不为空!")
    @ApiModelProperty(value = "微信优惠券批次号", required = true)
    private String stockId;

    @NotBlank(message = "微信优惠券创建时间 YYYY-MM-DDTHH:mm:ss+TIMEZONE不为空!")
    @ApiModelProperty(value = "微信优惠券创建时间 YYYY-MM-DDTHH:mm:ss+TIMEZONE", required = true)
    private String createTime;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}