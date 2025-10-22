package com.weikbest.pro.saas.merchat.coupon.module.vo;

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
@ApiModel(value = "CouponVO对象", description = "优惠券表")
public class CouponVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("优惠券内部备注")
    private String tips;

    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    private String couponType;

    @ApiModelProperty("优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消")
    private String couponStatus;

    @ApiModelProperty("活动开始时间")
    private Date eventStartTime;

    @ApiModelProperty("活动结束时间")
    private Date eventEndTime;

    @ApiModelProperty("优惠券发行总量")
    private Integer couponNum;

    @ApiModelProperty("适用商品类型 1-全部商品 2-部分商品")
    private String couponProdType;

    @ApiModelProperty("使用门槛 0-无使用门槛 1-订单满多少金额可用")
    private String couponUseType;

    @ApiModelProperty("使用门槛价格（元）")
    private BigDecimal couponUsePrice;

    @ApiModelProperty("核销小程序ID")
    private String appId;

    @ApiModelProperty("优惠金额（元）")
    private BigDecimal discountAmount;

    @ApiModelProperty("领劵开始时间")
    private Date getStartTime;

    @ApiModelProperty("领劵结束时间")
    private Date getEndTime;

    @ApiModelProperty("每人限领次数")
    private Integer restrictCount;

    @ApiModelProperty("领取人限制类型 1-不限制，所有人可领取 2-指定客户领取")
    private String restrictUserType;

    @ApiModelProperty("是否公开 0-否 1-是")
    private String isOpen;

    @ApiModelProperty("用劵开始时间")
    private Date useStartTime;

    @ApiModelProperty("用劵结束时间")
    private Date useEndTime;

    @ApiModelProperty("生效类型 1-领券后立即生效 2-延迟生效")
    private String enableType;

    @ApiModelProperty("领券（天）后生效")
    private Integer delayEnableDay;

    @ApiModelProperty("有效期（天）")
    private Integer validityDay;

    @ApiModelProperty("用券页面链接")
    private String couponUseUrl;

    @ApiModelProperty("优惠券样式 1-默认图片 2-上传图片")
    private String couponThemeType;

    @ApiModelProperty("卡包详情图片链接")
    private String couponImageUrl;

    @ApiModelProperty("卡包详情图片链接(oss存储)")
    private String couponImageOssurl;

    @ApiModelProperty("卡包图标链接")
    private String merchatLogoUrl;

    @ApiModelProperty("卡包图标链接(oss存储)")
    private String merchatLogoOssUrl;

    @ApiModelProperty("商户创建批次凭据号")
    private String outRequestNo;

    @ApiModelProperty("微信优惠券批次号")
    private String stockId;

    @ApiModelProperty("微信优惠券创建时间 YYYY-MM-DDTHH:mm:ss+TIMEZONE")
    private String createTime;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("领取比例")
    private BigDecimal getPercentage;


}
