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
@ApiModel(value = "OrderReceiveRecordQO对象", description = "订单分账记录表")
public class OrderReceiveRecordQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("分账接收方类型 1-商户 3-平台")
    private String businessType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("微信分账订单ID")
    private String wxOrderId;

    @ApiModelProperty("商户分账单号")
    private String outTradeNo;

    @ApiModelProperty("微信支付订单号")
    private String transactionId;

    @ApiModelProperty("分账接收方账号")
    private String mchId;

    @ApiModelProperty("分账金额（分）")
    private Integer amount;

    @ApiModelProperty("分账发生时间 yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    @ApiModelProperty("分账明细单号")
    private String detailId;

    @ApiModelProperty("分账单状态 PROCESSING-处理中  FINISHED-分账完成")
    private String receiveStates;

    @ApiModelProperty("分账结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭")
    private String receiveResult;

    @ApiModelProperty("分账失败原因 1、ACCOUNT_ABNORMAL：分账接收账户异常	              2、NO_RELATION：分账关系已解除	              3、RECEIVER_HIGH_RISK：高风险接收方	              4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名	              5、NO_AUTH：分账权限已解除	              6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额	              7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常")
    private String receiveFailReason;

    @ApiModelProperty("分账状态 0-分账中 1-已分账 2-回退中 3-已回退 4-分账失败 5-回退失败 6-已关闭")
    private String receiveRecordStatus;

    @ApiModelProperty("回退类型 1-平台分账扣款 2-平台分账回退 3-平台售后分账回退 4-技术服务费回退")
    private String receiveType;

    @ApiModelProperty("回退备注 例：押金扣除平台服务费XX元")
    private String description;

    @ApiModelProperty("分账比例")
    private BigDecimal receiveScale;


}