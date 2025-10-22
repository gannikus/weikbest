package com.weikbest.pro.saas.merchat.complaint.module.qo;

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
@ApiModel(value = "OrderComplaintQO对象", description = "订单投诉表")
public class OrderComplaintQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("投诉类型 1-微信支付投诉 2-店铺投诉")
    private String complaintType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @ApiModelProperty("关联商品名称")
    private String prodName;

    @ApiModelProperty("投诉原因 1-商家发错货 2-商品质量问题 3-商品规格选错了 4-其他原因")
    private String complaintReason;

    @ApiModelProperty("投诉编号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("投诉处理状态（商家处理）0-未处理 1-处理中 2-已处理")
    private String complaintBusiStatus;

    @ApiModelProperty("客户联系电话")
    private String customerPhone;

    @ApiModelProperty("微信商户号")
    private String mchId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("客户名称")
    private String tpName;

    @ApiModelProperty("投诉发起开始时间")
    private Date applyStartTime;

    @ApiModelProperty("投诉发起结束时间")
    private Date applyEndTime;

}