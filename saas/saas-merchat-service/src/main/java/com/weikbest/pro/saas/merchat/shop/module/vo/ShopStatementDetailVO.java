package com.weikbest.pro.saas.merchat.shop.module.vo;

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
 * 店铺对账单明细表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopStatementDetailVO对象", description = "店铺对账单明细表")
public class ShopStatementDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("对账单类型 1-卖出交易 2-退款交易")
    private String statementType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty(value = "订单号")
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后单号")
    private Long orderAfterSaleId;

    @ApiModelProperty("退款金额")
    private String refundAmount;

    @ApiModelProperty("退款时间 yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    @ApiModelProperty("支付流水号")
    private String payTraceId;

    @ApiModelProperty("结算状态 0-未结算 1-已结算")
    private String settleType;

    @ApiModelProperty("支付渠道 1-微信支付")
    private String payType;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("实付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("备注")
    private String description;


}