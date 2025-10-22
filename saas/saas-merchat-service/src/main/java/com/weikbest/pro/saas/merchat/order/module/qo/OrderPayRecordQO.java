package com.weikbest.pro.saas.merchat.order.module.qo;

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
 * 订单支付记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderPayRecordQO对象", description = "订单支付记录表")
public class OrderPayRecordQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    private Long orderId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID")
    private Long customerId;

    @ApiModelProperty("付款金额")
    private BigDecimal payAmount;

    @ApiModelProperty("付款方式 1- 微信支付")
    private String payMode;

    @ApiModelProperty("付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败")
    private String payStatus;

    @ApiModelProperty("付款时间 yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("支付有效时长 秒级 取商品的有效时长")
    private Integer payActivityPeriod;

    @ApiModelProperty("付款内容")
    private String payContent;

    @ApiModelProperty("流水号")
    private String tradingFlow;

    @ApiModelProperty("支付订单号")
    private String outTradeNo;

    @ApiModelProperty("支付返回内容")
    private String payRespContent;

    @ApiModelProperty("支付回调返回内容")
    private String callbackContent;

    @ApiModelProperty("支付回调返回时间")
    private Date callbackTime;

    @ApiModelProperty("退款号")
    private String refundTradeNo;

    @ApiModelProperty("退款状态 0-未退款 1-已退款 2-退款中")
    private String refundStatus;

    @ApiModelProperty("退款返回内容")
    private String refundRespContent;

    @ApiModelProperty("退款回调返回内容")
    private String refundCallbackContent;

    @ApiModelProperty("退款回调返回时间")
    private Date refundCallbackTime;

    @ApiModelProperty("退款流水号")
    private String refundId;

    @ApiModelProperty("是否结算 0-否 1-是")
    private String isSettle;

    @ApiModelProperty("备注")
    private String description;


}