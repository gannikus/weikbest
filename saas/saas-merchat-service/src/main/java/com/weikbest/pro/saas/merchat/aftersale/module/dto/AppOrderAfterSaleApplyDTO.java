package com.weikbest.pro.saas.merchat.aftersale.module.dto;

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
 * 订单售后申请列表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderAfterSaleApplyDTO对象", description = "订单售后申请列表")
public class AppOrderAfterSaleApplyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单ID")
    private String id;

    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("支付运费")
    private BigDecimal payCarriage;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("商品销售套餐属性组合（JSON）")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal prodComboPrice;

    @ApiModelProperty("商品图片")
    private String prodImg;

    @ApiModelProperty("数量")
    private Integer buyNumber;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前售后服务ID")
    private Long orderAfterSaleId;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("企业微信客服")
    private String wxBusiness;
}