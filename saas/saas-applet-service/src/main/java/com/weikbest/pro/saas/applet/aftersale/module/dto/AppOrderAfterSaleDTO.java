package com.weikbest.pro.saas.applet.aftersale.module.dto;

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
import java.util.List;

/**
 * <p>
 * 客户售后信息
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppCustAfterSaleDTO对象", description = "售后信息")
public class AppOrderAfterSaleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    private Long customerId;

    @ApiModelProperty("收货状态 0-未收货 1-已收货")
    private String takeDeliveryType;

    @ApiModelProperty("申请类型 1-仅退款 2-退货退款 3-换货")
    private String applyType;

    @ApiModelProperty("申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他")
    private String applyReason;

    @ApiModelProperty("货物件数")
    private Integer goodsNum;

    @ApiModelProperty("申请金额")
    private BigDecimal applyAmount;

    @ApiModelProperty("联系电话")
    private String customerPhone;

    @ApiModelProperty("问题描述")
    private String questionDetail;

    @ApiModelProperty("售后申请图片集合")
    private List<String> imgpaths;

}