package com.weikbest.pro.saas.applet.complaint.module.dto;

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
import java.util.List;

/**
 * <p>
 * 订单投诉表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-29
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderComplaintDTO对象", description = "订单投诉表")
public class AppOrderComplaintDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @ApiModelProperty("投诉类型 1-微信支付投诉 2-店铺投诉")
    private String complaintType;

    @ApiModelProperty("投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题")
    private String complaintReason;

    @ApiModelProperty("客户联系电话")
    private String customerPhone;

    @ApiModelProperty(value = "反馈原因")
    private String feedbackReason;

    @ApiModelProperty("客户反馈图片")
    private List<String> complaintImgs;

}