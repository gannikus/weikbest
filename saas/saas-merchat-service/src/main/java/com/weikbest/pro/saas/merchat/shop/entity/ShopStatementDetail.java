package com.weikbest.pro.saas.merchat.shop.entity;

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
@TableName("t_mmdm_shop_statement_detail")
@ApiModel(value = "ShopStatementDetail对象", description = "店铺对账单明细表")
public class ShopStatementDetail implements Serializable {

    public static final String ID = "id";
    public static final String STATEMENT_TYPE = "statement_type";
    public static final String SHOP_ID = "shop_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String NUMBER = "number";
    public static final String ORDER_AFTER_SALE_ID = "order_after_sale_id";
    public static final String REFUND_AMOUNT = "refund_amount";
    public static final String REFUND_TIME = "refund_time";
    public static final String PROD_NAME = "prod_name";
    public static final String ORDER_TIME = "order_time";
    public static final String PAY_TRACE_ID = "pay_trace_id";
    public static final String SETTLE_TYPE = "settle_type";
    public static final String PAY_TYPE = "pay_type";
    public static final String PAY_TIME = "pay_time";
    public static final String PAY_AMOUNT = "pay_amount";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("对账单类型 1-卖出交易 2-退款交易")
    @TableField(value = "statement_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String statementType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("订单号")
    @TableField("number")
    private String number;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后单号")
    @TableField("order_after_sale_id")
    private Long orderAfterSaleId;
    @ApiModelProperty("退款金额")
    @TableField(value = "refund_amount", insertStrategy = FieldStrategy.NOT_EMPTY)
    private BigDecimal refundAmount;
    @ApiModelProperty("退款时间 yyyy-MM-dd HH:mm:ss")
    @TableField("refund_time")
    private Date refundTime;
    @ApiModelProperty("商品名称")
    @TableField(value = "prod_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodName;
    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    @TableField("order_time")
    private Date orderTime;
    @ApiModelProperty("支付流水号")
    @TableField(value = "pay_trace_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payTraceId;
    @ApiModelProperty("结算状态 0-未结算 1-已结算")
    @TableField(value = "settle_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String settleType;
    @ApiModelProperty("支付渠道 1-微信支付")
    @TableField(value = "pay_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payType;
    @ApiModelProperty("支付时间")
    @TableField("pay_time")
    private Date payTime;
    @ApiModelProperty("实付金额")
    @TableField("pay_amount")
    private BigDecimal payAmount;
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
