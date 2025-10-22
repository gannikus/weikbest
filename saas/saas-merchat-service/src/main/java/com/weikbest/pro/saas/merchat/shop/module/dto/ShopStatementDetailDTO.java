package com.weikbest.pro.saas.merchat.shop.module.dto;

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
@ApiModel(value = "ShopStatementDetailDTO对象", description = "店铺对账单明细表")
public class ShopStatementDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "对账单类型 1-卖出交易 2-退款交易不为空!")
    @ApiModelProperty(value = "对账单类型 1-卖出交易 2-退款交易", required = true)
    private String statementType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @NotNull(message = "订单号不为空!")
    @ApiModelProperty(value = "订单号", required = true)
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后单号")
    private Long orderAfterSaleId;

    @ApiModelProperty("退款金额")
    private String refundAmount;

    @ApiModelProperty("退款时间 yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

    @NotBlank(message = "商品名称不为空!")
    @ApiModelProperty(value = "商品名称", required = true)
    private String prodName;

    @ApiModelProperty(value = "下单时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date orderTime;

    @NotBlank(message = "支付流水号不为空!")
    @ApiModelProperty(value = "支付流水号", required = true)
    private String payTraceId;

    @NotBlank(message = "结算状态 0-未结算 1-已结算不为空!")
    @ApiModelProperty(value = "结算状态 0-未结算 1-已结算", required = true)
    private String settleType;

    @NotBlank(message = "支付渠道 1-微信支付不为空!")
    @ApiModelProperty(value = "支付渠道 1-微信支付", required = true)
    private String payType;

    @ApiModelProperty(value = "支付时间", required = true)
    private Date payTime;

    @ApiModelProperty(value = "实付金额", required = true)
    private BigDecimal payAmount;

    @ApiModelProperty("备注")
    private String description;


}