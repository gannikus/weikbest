package com.weikbest.pro.saas.merchat.aftersale.module.excel;

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
public class OrderAfterSaleDetailExcel implements Serializable {

    @ExcelProperty("店铺名称")
    private String shopName;

    @ExcelProperty("店铺编码")
    private String shopNumber;

    @ExcelProperty("售后编号")
    private String afterSaleNumber;

    @ExcelProperty("售后方式")
    private String afterSaleApplyType;

    @ExcelProperty("订单编号")
    private String orderNumber;

    @ExcelProperty("订单来源")
    private String orderSource;

    @ExcelProperty("商品名称")
    private String prodName;

    @ExcelProperty("商品规格")
    private String prodComboTitle;

    @ExcelProperty("商品ID")
    private Long prodId;

    @ExcelProperty("发货物流公司")
    private String logisticsCompany;

    @ExcelProperty("发货物流单号")
    private String courierNumber;

    @ExcelProperty("付款时间")
    private String payTime;

    @ExcelProperty("订单金额")
    private BigDecimal orderAmount;

//    @ExcelProperty("付款金额")
//    private BigDecimal payAmount;

    @ExcelProperty("退款金额")
    private BigDecimal applyAmount;

    @ExcelProperty("售后申请数量")
    private Integer goodsNum;

    @ExcelProperty("售后申请时间")
    private String applyTime;

    @ExcelProperty("售后状态")
    private String afterSaleStatus;

    @ExcelProperty("申请原因")
    private String applyReason;

    @ExcelIgnore
    @ExcelProperty("订单状态")
    private String orderStatus;

    @ExcelProperty("发货状态")
    private String sendFlag;

    @ExcelProperty("退货地址")
    private String backAddress;

    @ExcelProperty("退换货物流公司")
    private String backLogisticsCompany;

    @ExcelProperty("退换货物流单号")
    private String backCourierNumber;

    @ExcelProperty("买家下单备注")
    private String customerDescription;

    @ExcelProperty("商家订单备注")
    private String description;


}
