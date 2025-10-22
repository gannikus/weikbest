package com.weikbest.pro.saas.merchat.aftersale.module.dto;

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

/**
 * <p>
 * 订单售后申请列表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderAfterSaleApplyDTO对象", description = "订单售后申请列表")
public class AppOrderAfterSaleListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售金额")
    private BigDecimal prodSkuPrice;

    @ApiModelProperty("商品图片")
    private String prodImg;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前售后服务ID")
    private Long orderAfterSaleId;

    @ApiModelProperty("处理中，0-否  1-是")
    private String inProcess;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("申请类型 1-仅退款 2-退货退款 3-换货")
    private String applyType;

    @ApiModelProperty("申请金额")
    private BigDecimal applyAmount;

    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;

    @ApiModelProperty("数量")
    private Integer buyNumber;

    @ApiModelProperty("企业微信客服")
    private String wxBusiness;

}