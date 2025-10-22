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
@ApiModel(value = "OrderPayRecordDTO对象", description = "订单支付记录表")
public class OrderPayRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联订单ID不为空!")
    @ApiModelProperty(value = "关联订单ID", required = true)
    private Long orderId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID不为空!")
    @ApiModelProperty(value = "关联客户ID", required = true)
    private Long customerId;

    @ApiModelProperty(value = "付款金额", required = true)
    private BigDecimal payAmount;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @NotBlank(message = "付款方式 1- 微信支付不为空!")
    @ApiModelProperty(value = "付款方式 1- 微信支付", required = true)
    private String payType;

    @NotBlank(message = "付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败不为空!")
    @ApiModelProperty(value = "付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败", required = true)
    private String payStatus;

    @ApiModelProperty(value = "付款时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date payTime;

    @ApiModelProperty(value = "支付有效时长 秒级 取商品的有效时长", required = true)
    private Integer payActivityPeriod;

    @NotBlank(message = "付款内容不为空!")
    @ApiModelProperty(value = "付款内容", required = true)
    private String payContent;

    @NotBlank(message = "流水号不为空!")
    @ApiModelProperty(value = "流水号", required = true)
    private String tradingFlow;

    @NotBlank(message = "支付订单号不为空!")
    @ApiModelProperty(value = "支付订单号", required = true)
    private String outTradeNo;

    @NotBlank(message = "支付返回内容不为空!")
    @ApiModelProperty(value = "支付返回内容", required = true)
    private String payRespContent;

    @NotBlank(message = "支付回调返回内容不为空!")
    @ApiModelProperty(value = "支付回调返回内容", required = true)
    private String callbackContent;

    @ApiModelProperty(value = "支付回调返回时间", required = true)
    private Date callbackTime;

    @NotBlank(message = "退款号不为空!")
    @ApiModelProperty(value = "退款号", required = true)
    private String refundTradeNo;

    @NotBlank(message = "退款状态 0-未退款 1-已退款 2-退款中不为空!")
    @ApiModelProperty(value = "退款状态 0-未退款 1-已退款 2-退款中", required = true)
    private String refundStatus;

    @NotBlank(message = "退款返回内容不为空!")
    @ApiModelProperty(value = "退款返回内容", required = true)
    private String refundRespContent;

    @NotBlank(message = "退款回调返回内容不为空!")
    @ApiModelProperty(value = "退款回调返回内容", required = true)
    private String refundCallbackContent;

    @ApiModelProperty(value = "退款回调返回时间", required = true)
    private Date refundCallbackTime;

    @ApiModelProperty("退款流水号")
    private String refundId;

    @ApiModelProperty("是否结算 0-否 1-是")
    private String isSettle;

    @ApiModelProperty("备注")
    private String description;


}
