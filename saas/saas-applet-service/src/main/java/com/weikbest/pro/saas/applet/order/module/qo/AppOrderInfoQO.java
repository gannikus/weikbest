package com.weikbest.pro.saas.applet.order.module.qo;

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
@ApiModel(value = "AppOrderInfoQO对象", description = "订单表")
public class AppOrderInfoQO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品销售属性ID")
    private Long prodSkuId;

    @ApiModelProperty("商品销售属性标题")
    private String prodSkuName;

    @ApiModelProperty("关联商品属性组合（JSON）")
    private String prodSkuAttrValues;

    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private String orderSource;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

}