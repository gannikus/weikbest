package com.weikbest.pro.saas.merchat.order.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "OrderReceiveRecordDTO对象", description = "订单分账记录表")
public class OrderReceiveRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "订单号 规则为年月日时分秒豪秒不为空!")
    @ApiModelProperty(value = "订单号 规则为年月日时分秒豪秒", required = true)
    private String number;

    @NotBlank(message = "分账接收方类型 1-商户 3-平台不为空!")
    @ApiModelProperty(value = "分账接收方类型 1-商户 3-平台", required = true)
    private String businessType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "微信分账订单ID不为空!")
    @ApiModelProperty(value = "微信分账订单ID", required = true)
    private String wxOrderId;

    @NotBlank(message = "商户分账单号不为空!")
    @ApiModelProperty(value = "商户分账单号", required = true)
    private String outTradeNo;

    @NotBlank(message = "微信支付订单号不为空!")
    @ApiModelProperty(value = "微信支付订单号", required = true)
    private String transactionId;

    @NotBlank(message = "分账接收方账号不为空!")
    @ApiModelProperty(value = "分账接收方账号", required = true)
    private String mchId;

    @ApiModelProperty(value = "分账金额（分）", required = true)
    private Integer amount;

    @ApiModelProperty(value = "分账发生时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date receiveTime;

    @NotBlank(message = "分账明细单号不为空!")
    @ApiModelProperty(value = "分账明细单号", required = true)
    private String detailId;

    @NotBlank(message = "分账单状态 PROCESSING-处理中  FINISHED-分账完成不为空!")
    @ApiModelProperty(value = "分账单状态 PROCESSING-处理中  FINISHED-分账完成", required = true)
    private String receiveStates;

    @NotBlank(message = "分账结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭不为空!")
    @ApiModelProperty(value = "分账结果 PENDING-待分账 SUCCESS-分账成功  CLOSED-已关闭", required = true)
    private String receiveResult;

    @NotBlank(message = "分账失败原因 1、ACCOUNT_ABNORMAL：分账接收账户异常	              2、NO_RELATION：分账关系已解除	              3、RECEIVER_HIGH_RISK：高风险接收方	              4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名	              5、NO_AUTH：分账权限已解除	              6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额	              7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常不为空!")
    @ApiModelProperty(value = "分账失败原因 1、ACCOUNT_ABNORMAL：分账接收账户异常	              2、NO_RELATION：分账关系已解除	              3、RECEIVER_HIGH_RISK：高风险接收方	              4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名	              5、NO_AUTH：分账权限已解除	              6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额	              7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常", required = true)
    private String receiveFailReason;

    @NotBlank(message = "分账状态 0-分账中 1-已分账 2-回退中 3-已回退 4-分账失败 5-回退失败 6-已关闭不为空!")
    @ApiModelProperty(value = "分账状态 0-分账中 1-已分账 2-回退中 3-已回退 4-分账失败 5-回退失败 6-已关闭", required = true)
    private String receiveRecordStatus;

    @ApiModelProperty("回退类型  1-平台分账扣款 2-平台分账回退 3-平台售后分账回退 4-技术服务费回退")
    private String receiveType;

    @ApiModelProperty("备注 例：押金扣除平台服务费XX元")
    private String description;

    @ApiModelProperty("分账比例")
    private BigDecimal receiveScale;

}