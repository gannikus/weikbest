package com.weikbest.pro.saas.merchat.complaint.module.dto;

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
import java.util.Date;

/**
 * <p>
 * 订单投诉表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintDTO对象", description = "订单投诉表")
public class OrderComplaintDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "投诉编号 规则为年月日时分秒豪秒不为空!")
    @ApiModelProperty(value = "投诉编号 规则为年月日时分秒豪秒", required = true)
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单ID不为空!")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单售后ID不为空!")
    @ApiModelProperty(value = "订单售后ID", required = true)
    private Long orderAfterSaleId;

    @NotBlank(message = "关联小程序ID不为空!")
    @ApiModelProperty(value = "关联小程序ID", required = true)
    private String appId;

    @NotBlank(message = "关联微信支付商户号ID不为空!")
    @ApiModelProperty(value = "关联微信支付商户号ID", required = true)
    private String mchId;

    @NotBlank(message = "下单小程序类型来源 1-微信小程序不为空!")
    @ApiModelProperty(value = "下单小程序类型来源 1-微信小程序", required = true)
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID不为空!")
    @ApiModelProperty(value = "关联商品ID", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @ApiModelProperty(value = "申请时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date applyTime;

    @NotBlank(message = "投诉类型 1-微信支付投诉 2-店铺投诉不为空!")
    @ApiModelProperty(value = "投诉类型 1-微信支付投诉 2-店铺投诉", required = true)
    private String complaintType;

    @NotBlank(message = "投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题不为空!")
    @ApiModelProperty(value = "投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题", required = true)
    private String complaintReason;

    @NotBlank(message = "投诉状态（商家处理）0-未处理 1-处理中 2-已处理不为空!")
    @ApiModelProperty(value = "投诉状态（商家处理）0-未处理 1-处理中 2-已处理", required = true)
    private String complaintBusiStatus;

    @NotBlank(message = "投诉状态（用户认可）0-不认可 1-认可不为空!")
    @ApiModelProperty(value = "投诉状态（用户认可）0-不认可 1-认可", required = true)
    private String complaintCustStatus;

    @NotBlank(message = "投诉状态（平台介入）0-未介入 1-已介入不为空!")
    @ApiModelProperty(value = "投诉状态（平台介入）0-未介入 1-已介入", required = true)
    private String complaintPlatformStatus;

    @NotBlank(message = "投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉不为空!")
    @ApiModelProperty(value = "投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉", required = true)
    private String complaintStatus;

    @NotBlank(message = "客户联系电话不为空!")
    @ApiModelProperty(value = "客户联系电话", required = true)
    private String customerPhone;

    @NotBlank(message = "反馈原因不为空!")
    @ApiModelProperty(value = "反馈原因", required = true)
    private String feedbackReason;

    @NotBlank(message = "沟通内容不为空!")
    @ApiModelProperty(value = "沟通内容", required = true)
    private String complaintContent;

    @ApiModelProperty(value = "完成时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date finishTime;

    @ApiModelProperty("投诉对象")
    private String complaintObject;

    @ApiModelProperty("商品展示图（缩略图）")
    private String showImg;

    @ApiModelProperty("备注")
    private String description;


}