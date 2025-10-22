package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderInfoListVO对象", description = "订单分页查询对象")
public class OrderInfoListVO extends OrderInfoVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /** 其他附表信息 */
    /**
     * 商品信息
     */
    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品销售套餐名称")
    private String prodComboTitle;

    @ApiModelProperty("商品销售套餐属性组合（JSON）")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal prodComboPrice;

    @ApiModelProperty("购买数量")
    private Integer buyNumber;

    @ApiModelProperty("商品图片")
    private String prodImg;

    @ApiModelProperty(value = "商品内部备注")
    private String tips;

    /**
     * 售后信息
     */
    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;

    /**
     * 买家收货人信息
     */
    @ApiModelProperty("客户微信名称")
    private String tpName;

    @ApiModelProperty("收货人姓名")
    private String consignee;

    @ApiModelProperty("收货人手机号")
    private String consigneePhone;

    @ApiModelProperty("收货地址 省、直辖市")
    private String addrProvince;

    @ApiModelProperty("收货地址 市")
    private String addrCity;

    @ApiModelProperty("收货地址 区、县")
    private String addrDistrict;

    @ApiModelProperty("详细地址")
    private String addr;

    @ApiModelProperty("客户备注")
    private String customerDescription;

    /**
     * 配送方式/发货时间
     */
    @ApiModelProperty("物流公司")
    private String logisticsCompany;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("发货时间")
    private Date logisticsTime;

    /**
     * 支付信息
     */
    @ApiModelProperty("付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败")
    private String payStatus;

    @ApiModelProperty("支付流水号")
    private String tradingFlow;

    @ApiModelProperty("支付订单号")
    private String outTradeNo;


    /**
     * 店铺信息
     */
    @ApiModelProperty("店铺名称")
    private String shopName;
}