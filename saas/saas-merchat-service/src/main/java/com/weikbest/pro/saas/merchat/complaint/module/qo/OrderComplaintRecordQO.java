package com.weikbest.pro.saas.merchat.complaint.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单投诉处理记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintRecordQO对象", description = "订单投诉处理记录表")
public class OrderComplaintRecordQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("投诉编号 规则为年月日时分秒豪秒")
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单售后ID")
    private Long orderAfterSaleId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("关联微信支付商户号ID")
    private String mchId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("投诉类型 1-微信支付投诉 2-店铺投诉")
    private String complaintType;

    @ApiModelProperty("投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题")
    private String complaintReason;

    @ApiModelProperty("投诉状态（商家处理）0-未处理 1-处理中 2-已处理")
    private String complaintBusiStatus;

    @ApiModelProperty("投诉状态（用户认可）0-不认可 1-认可")
    private String complaintCustStatus;

    @ApiModelProperty("投诉状态（平台介入）0-未介入 1-已介入")
    private String complaintPlatformStatus;

    @ApiModelProperty("投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 109-商家处理超时 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉")
    private String complaintStatus;

    @ApiModelProperty("客户联系电话")
    private String customerPhone;

    @ApiModelProperty(value = "反馈原因")
    private String feedbackReason;

    @ApiModelProperty(value = "沟通内容")
    private String complaintContent;


    @ApiModelProperty("申请时间 yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    @ApiModelProperty("商家处理时间 yyyy-MM-dd HH:mm:ss")
    private Date busiOperateTime;

    @ApiModelProperty("客户处理时间 yyyy-MM-dd HH:mm:ss")
    private Date custOperateTime;

    @ApiModelProperty("完成时间 yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    @ApiModelProperty("投诉对象")
    private String complaintObject;

    @ApiModelProperty("商品展示图（缩略图）")
    private String showImg;

    @ApiModelProperty("变更人类型 0-平台用户 1-商家端用户 2-App端用户")
    private String changerUserType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("变更人ID")
    private Long changerUserId;

    @ApiModelProperty("变更时间 yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

    @ApiModelProperty("备注")
    private String description;


}