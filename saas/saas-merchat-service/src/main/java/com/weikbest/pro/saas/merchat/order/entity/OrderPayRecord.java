package com.weikbest.pro.saas.merchat.order.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("t_mmdm_order_pay_record")
@ApiModel(value = "OrderPayRecord对象", description = "订单支付记录表")
public class OrderPayRecord implements Serializable {

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String PAY_AMOUNT = "pay_amount";
    public static final String TOTAL_AMOUNT = "total_amount";
    public static final String PAY_MODE = "pay_mode";
    public static final String PAY_STATUS = "pay_status";
    public static final String PAY_TIME = "pay_time";
    public static final String PAY_ACTIVITY_PERIOD = "pay_activity_period";
    public static final String PAY_CONTENT = "pay_content";
    public static final String TRADING_FLOW = "trading_flow";
    public static final String OUT_TRADE_NO = "out_trade_no";
    public static final String PAY_RESP_CONTENT = "pay_resp_content";
    public static final String CALLBACK_CONTENT = "callback_content";
    public static final String CALLBACK_TIME = "callback_time";
    public static final String REFUND_TRADE_NO = "refund_trade_no";
    public static final String REFUND_AMOUNT = "refund_amount";
    public static final String REFUND_REASON = "refund_reason";
    public static final String REFUND_STATUS = "refund_status";
    public static final String REFUND_TIME = "refund_time";
    public static final String REFUND_RESP_CONTENT = "refund_resp_content";
    public static final String REFUND_CALLBACK_CONTENT = "refund_callback_content";
    public static final String REFUND_CALLBACK_TIME = "refund_callback_time";
    public static final String IS_SETTLE = "is_settle";
    public static final String REFUND_ID = "refund_id";
    public static final String UNFREEZE_TRADE_NO = "unfreeze_trade_no";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    @TableField("order_id")
    private Long orderId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("付款金额")
    @TableField("pay_amount")
    private BigDecimal payAmount;
    @ApiModelProperty("订单总金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;
    @ApiModelProperty("付款方式 1- 微信支付")
    @TableField(value = "pay_mode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payMode;
    @ApiModelProperty("付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败")
    @TableField(value = "pay_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payStatus;
    @ApiModelProperty("付款时间 yyyy-MM-dd HH:mm:ss")
    @TableField("pay_time")
    private Date payTime;
    @ApiModelProperty("支付有效时长 秒级 取商品的有效时长")
    @TableField("pay_activity_period")
    private Integer payActivityPeriod;
    @ApiModelProperty("付款内容")
    @TableField(value = "pay_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payContent;
    @ApiModelProperty("流水号")
    @TableField(value = "trading_flow", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tradingFlow;
    @ApiModelProperty("支付订单号")
    @TableField(value = "out_trade_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String outTradeNo;
    @ApiModelProperty("支付返回内容")
    @TableField(value = "pay_resp_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payRespContent;
    @ApiModelProperty("支付回调返回内容")
    @TableField(value = "callback_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String callbackContent;
    @ApiModelProperty("支付回调返回时间")
    @TableField("callback_time")
    private Date callbackTime;
    @ApiModelProperty("退款号")
    @TableField(value = "refund_trade_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundTradeNo;
    @ApiModelProperty("退款金额")
    @TableField(value = "refund_amount", insertStrategy = FieldStrategy.NOT_NULL)
    private BigDecimal refundAmount;
    @ApiModelProperty("退款原因")
    @TableField(value = "refund_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundReason;
    @ApiModelProperty("退款状态 0-未退款 1-已退款 2-退款中")
    @TableField(value = "refund_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundStatus;
    @ApiModelProperty("退款时间 yyyy-MM-dd HH:mm:ss")
    @TableField("refund_time")
    private Date refundTime;
    @ApiModelProperty("退款返回内容")
    @TableField(value = "refund_resp_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundRespContent;
    @ApiModelProperty("退款回调返回内容")
    @TableField(value = "refund_callback_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundCallbackContent;
    @ApiModelProperty("退款回调返回时间")
    @TableField("refund_callback_time")
    private Date refundCallbackTime;
    @ApiModelProperty("退款流水号")
    @TableField(value = "refund_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundId;
    @ApiModelProperty("解冻请求单号")
    @TableField(value = "unfreeze_trade_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String unfreezeTradeNo;
    @ApiModelProperty("是否结算 0-否 1-是")
    @TableField(value = "is_settle", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isSettle;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
