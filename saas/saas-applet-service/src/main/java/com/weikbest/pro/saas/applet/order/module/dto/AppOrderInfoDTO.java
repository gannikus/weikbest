package com.weikbest.pro.saas.applet.order.module.dto;

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

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderInfoDTO对象", description = "下单信息")
public class AppOrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单编号")
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @ApiModelProperty("openid")
    private String tpOpenid;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("商品销售套餐属性组合（JSON）{\"attrname\":attrValue,\"attrname\":attrValue}")
    private String prodComboAttrValues;

    @ApiModelProperty("用户行为 1-主动打开微信小程序 5-点击所投放的广告进入小程序 10-卡包回流优惠券 15-卡包平台券")
    private String userBehavior;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("商品图片")
    private String prodImg;

    @ApiModelProperty("数量")
    private Integer buyNumber;

    @ApiModelProperty("客户备注")
    private String customerDescription;

    @ApiModelProperty("第三方平台类型 1-微信")
    private String tpType;

    @ApiModelProperty("昵称")
    private String tpName;

    @ApiModelProperty("头像")
    private String tpPhoto;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("手机号 ")
    private String phone;

    @ApiModelProperty("收货地址 省、直辖市")
    private String addrProvince;

    @ApiModelProperty("收货地址 市")
    private String addrCity;

    @ApiModelProperty("收货地址 区、县")
    private String addrDistrict;

    @ApiModelProperty("详细地址 ")
    private String addr;


    @ApiModelProperty("开启订单发货通知 0-否 1-是")
    private String deliverNotify;

    @ApiModelProperty("开启订单待付款通知 0-否 1-是")
    private String waitPayNotify;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户领用优惠券记录ID 核销使用")
    private Long custCouponRestrictId ;

    @ApiModelProperty("支付运费")
    private BigDecimal payCarriage;

    @ApiModelProperty("下单路径 1-小程序直接成交 5-小程序支付失败成交 20-广告直接提交 25-广告支付失败成交 30-左滑操作直接成交 35-左滑操作支付失败成交 40-聚合页首产品直接成交 45-聚合页首产品支付失败成交 50-聚合页非首产品直接成交 55-聚合页非首产品支付失败成交 60-聚合页商户主页直接成交 65-聚合页商户主页支付失败成交 70-回流优惠券页直接成交 75-回流优惠券页支付失败成交 80-限时优惠直接成交 85-限时优惠支付失败成交")
    private String orderPath;

    @ApiModelProperty("订单来源商品_prod_id")
    private Long sourceProdId;

    @ApiModelProperty("订单来源优惠券_coupon_code")
    private String sourceCouponCode ;

    @ApiModelProperty("广告id/广告计划id")
    private String adAid;

    @ApiModelProperty("点击id")
    private String clickId;

    @ApiModelProperty("关联商品返回页ID 回传使用，非返回页下单可以不传")
    private Long prodReturnPageId;

    @ApiModelProperty("支付方式 ，1-微信支付，2-货到付款")
    private String payType;

    @ApiModelProperty("到付应付金额")
    private BigDecimal deliveryPayAmount;

    @ApiModelProperty("是否有支付失败金额_0:没有 , 1:有")
    private Integer isThereAFailureMoney;
}
