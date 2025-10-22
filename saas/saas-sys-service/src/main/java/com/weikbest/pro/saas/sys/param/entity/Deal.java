package com.weikbest.pro.saas.sys.param.entity;

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
import java.util.Date;

/**
 * <p>
 * 系统交易规则表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_deal")
@ApiModel(value = "Deal对象", description = "系统交易规则表")
public class Deal implements Serializable {

    public static final String ID = "id";
    public static final String CLOSE_ORDER_MINUTE = "close_order_minute";
    public static final String AUTO_ORDER_RECEIVE = "auto_order_receive";
    public static final String AUTO_ORDER_COMPLETE = "auto_order_complete";
    public static final String FAST_REFUND_CONDITION1 = "fast_refund_condition1";
    public static final String FAST_REFUND_CONDITION2 = "fast_refund_condition2";
    public static final String FAST_REFUND_CONDITION3 = "fast_refund_condition3";
    public static final String IS_OPEN_CONDITION2 = "is_open_condition2";
    public static final String IS_OPEN_CONDITION3 = "is_open_condition3";
    public static final String REFUND_CUSTOMER_TIMEOUT = "refund_customer_timeout";
    public static final String REFUND_BUSINESS_TIMEOUT = "refund_business_timeout";
    public static final String REFUND_REPEAL_TIMEOUT = "refund_repeal_timeout";
    public static final String IS_OPEN_ORDER_COMMENT = "is_open_order_comment";
    public static final String IS_SHOW_ORDER_COMMENT = "is_show_order_comment";
    public static final String IS_REVIEW_ORDER_COMMENT = "is_review_order_comment";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("未付款自动关闭分钟数")
    @TableField("close_order_minute")
    private Integer closeOrderMinute;
    @ApiModelProperty("自动收货天数")
    @TableField("auto_order_receive")
    private Integer autoOrderReceive;
    @ApiModelProperty("收货后订单自动完成时间")
    @TableField("auto_order_complete")
    private Integer autoOrderComplete;
    @ApiModelProperty("极速退款条件：订单金额元以内")
    @TableField("fast_refund_condition1")
    private Integer fastRefundCondition1;
    @ApiModelProperty("极速退款条件：下单小时内")
    @TableField("fast_refund_condition2")
    private Integer fastRefundCondition2;
    @ApiModelProperty("极速退款条件：申请退款后未操作天数")
    @TableField("fast_refund_condition3")
    private Integer fastRefundCondition3;
    @ApiModelProperty("开启极速退款条件：下单小时内 0-否 1-是")
    @TableField(value = "is_open_condition2", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCondition2;
    @ApiModelProperty("开启极速退款条件：申请退款后未操作天数 0-否 1-是")
    @TableField(value = "is_open_condition3", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCondition3;
    @ApiModelProperty("买家未处理，系统自动关闭售后时间天")
    @TableField("refund_customer_timeout")
    private Integer refundCustomerTimeout;
    @ApiModelProperty("商家未处理，系统自动通过售后时间天")
    @TableField("refund_business_timeout")
    private Integer refundBusinessTimeout;
    @ApiModelProperty("维权被拒绝后，系统自动关闭维权时间天")
    @TableField("refund_repeal_timeout")
    private Integer refundRepealTimeout;
    @ApiModelProperty("开启订单评价 0-否 1-是")
    @TableField(value = "is_open_order_comment", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenOrderComment;
    @ApiModelProperty("显示订单评价 0-否 1-是")
    @TableField(value = "is_show_order_comment", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isShowOrderComment;
    @ApiModelProperty("审核订单评价 0-否 1-是")
    @TableField(value = "is_review_order_comment", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isReviewOrderComment;
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
