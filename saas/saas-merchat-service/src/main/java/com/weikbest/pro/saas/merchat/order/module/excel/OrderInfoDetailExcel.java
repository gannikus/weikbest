package com.weikbest.pro.saas.merchat.order.module.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/25
 */
@Getter
@Setter
@EqualsAndHashCode
public class OrderInfoDetailExcel implements Serializable {


    @ExcelProperty("店铺名称")
    private String shopName;

    @ExcelProperty("商户号")
    private Long businessId;

    @ExcelProperty("店铺编码")
    private String shopNumber;

    @ExcelProperty("订单号")
    private String orderNumber;

    @ExcelProperty("订单来源")
    private String orderSource;

    @ExcelProperty("广告渠道")
    private String advChannelType;

    @ExcelProperty("广告账户ID")
    private String advAccountId;

    @ExcelProperty("广告ID")
    private String adAid;

    @ExcelProperty("订单状态")
    private String orderStatus;

    @ExcelProperty("订单创建时间")
    private String orderTime;

    @ExcelProperty("买家付款时间")
    private String payTime;

    @ExcelProperty("发货时间")
    private String logisticsTime;

    @ExcelProperty("交易成功/关闭时间")
    private String orderFinishTime;

//    @ExcelProperty("付款方式")
//    private String payMode;

    @ExcelProperty("支付方式")
    private String payType;


    @ExcelProperty("支付流水号")
    private String tradingFlow;

    @ExcelProperty("商品总价")
    private BigDecimal orderAmount;

    @ExcelProperty("运费")
    private BigDecimal payCarriage;

    @ExcelProperty("优惠总额")
    private BigDecimal discountAmount;

    @ExcelProperty("实付金额")
    private BigDecimal payAmount;

//    @ExcelProperty("到付应付金额")
//    private String deliveryPayAmount;

    @ExcelProperty("商品ID")
    private Long prodId;

    @ExcelProperty("商品全名")
    private String prodName;

    @ExcelIgnore
    @ExcelProperty("商品购买型号")
    private String prodComboAttrValues;

    @ExcelIgnore
    @ExcelProperty("商品套餐名称")
    private String prodComboTitle;

    @ExcelProperty("商品规格")
    private String prodCombo;

    @ExcelProperty("规格编码/套餐编码")
    private Long prodComboId;

    @ExcelProperty("下单件数")
    private Integer buyNumber;

    @ExcelProperty("快递公司")
    private String logisticsCompany;

    @ExcelProperty("快递单号")
    private String courierNumber;

    @ExcelProperty("售后状态")
    private String afterSaleStatus;

    @ExcelProperty("售后类型")
    private String afterSaleApplyType;

    @ExcelProperty("收件人地址")
    private String consigneeAddr;

    @ExcelProperty("收件人")
    private String consignee;

    @ExcelProperty("收件人手机号")
    private String consigneePhone;

    @ExcelProperty("买家微信")
    private String tpName;

    @ExcelProperty("买家留言")
    private String customerDescription;

    @ExcelProperty("商家备注")
    private String description;
}
