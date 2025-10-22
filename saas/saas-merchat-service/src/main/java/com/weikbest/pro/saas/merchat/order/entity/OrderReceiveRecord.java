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
 * 订单分账记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_receive_record")
@ApiModel(value = "OrderReceiveRecord对象", description = "订单分账记录表")
public class OrderReceiveRecord implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String BUSINESS_TYPE = "business_type";
    public static final String WX_ORDER_ID = "wx_order_id";
    public static final String OUT_TRADE_NO = "out_trade_no";
    public static final String TRANSACTION_ID = "transaction_id";
    public static final String MCH_ID = "mch_id";
    public static final String AMOUNT = "amount";
    public static final String RECEIVE_TIME = "receive_time";
    public static final String DETAIL_ID = "detail_id";
    public static final String RECEIVE_STATES = "receive_states";
    public static final String RECEIVE_RESULT = "receive_result";
    public static final String RECEIVE_FAIL_REASON = "receive_fail_reason";
    public static final String RECEIVE_RECORD_STATUS = "receive_record_status";
    public static final String RECEIVE_TYPE = "receive_type";
    public static final String DESCRIPTION = "description";
    public static final String RECEIVE_SCALE = "receive_scale";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("分账接收方类型 1-商户 3-平台")
    @TableField(value = "business_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String businessType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("微信分账订单ID")
    @TableField("wx_order_id")
    private String wxOrderId;
    @ApiModelProperty("商户分账单号")
    @TableField(value = "out_trade_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String outTradeNo;
    @ApiModelProperty("微信支付订单号")
    @TableField(value = "transaction_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String transactionId;
    @ApiModelProperty("分账接收方账号")
    @TableField(value = "mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String mchId;
    @ApiModelProperty("分账金额（分）")
    @TableField("amount")
    private Integer amount;
    @ApiModelProperty("分账发生时间 yyyy-MM-dd HH:mm:ss")
    @TableField("receive_time")
    private Date receiveTime;
    @ApiModelProperty("分账明细单号")
    @TableField(value = "detail_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String detailId;
    @ApiModelProperty("分账单状态 PROCESSING-处理中  FINISHED-分账完成")
    @TableField(value = "receive_states", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiveStates;
    @ApiModelProperty("分账结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭")
    @TableField(value = "receive_result", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiveResult;
    @ApiModelProperty("分账失败原因 1、ACCOUNT_ABNORMAL：分账接收账户异常	              2、NO_RELATION：分账关系已解除	              3、RECEIVER_HIGH_RISK：高风险接收方	              4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名	              5、NO_AUTH：分账权限已解除	              6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额	              7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常")
    @TableField(value = "receive_fail_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiveFailReason;
    @ApiModelProperty("分账状态 0-分账中 1-已分账 2-回退中 3-已回退 4-分账失败 5-回退失败 6-已关闭 8-已清算")
    @TableField(value = "receive_record_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiveRecordStatus;
    @ApiModelProperty("回退类型 1-平台分账扣款 2-平台分账回退 3-平台售后分账回退 4-技术服务费回退")
    @TableField(value = "receive_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiveType;
    @ApiModelProperty("备注 例：押金扣除平台服务费XX元")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("分账比例")
    @TableField(value = "receive_scale", insertStrategy = FieldStrategy.NOT_EMPTY)
    private BigDecimal receiveScale;
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
