package com.weikbest.pro.saas.merchat.order.module.dto;

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
@ApiModel(value = "AppOrderInfoListDTO对象", description = "小程序端订单列表")
public class AppOrderInfoListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID")
    private Long id;

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

    @ApiModelProperty("订单名称")
    private String shopName;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("企业微信客服")
    private String wxBusiness;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("到付应付金额")
    private BigDecimal deliveryPayAmount;

}
