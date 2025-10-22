package com.weikbest.pro.saas.merchat.shop.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@ApiModel(value = "ShopStatementDetailQO对象", description = "店铺对账单明细表")
public class ShopStatementDetailQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

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

    @ApiModelProperty(value = "订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private String orderSource;

    @ApiModelProperty(value = "订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("结算状态 0-未结算 1-已结算")
    private String settleType;

    @ApiModelProperty("下单开始时间 yyyy-MM-dd HH:mm:ss")
    private Date orderStartTime;

    @ApiModelProperty("下单结束时间 yyyy-MM-dd HH:mm:ss")
    private Date orderEndTime;

}