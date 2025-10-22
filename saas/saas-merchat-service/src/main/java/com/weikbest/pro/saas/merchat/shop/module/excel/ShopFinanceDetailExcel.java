package com.weikbest.pro.saas.merchat.shop.module.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/25
 */
@Getter
@Setter
@EqualsAndHashCode
public class ShopFinanceDetailExcel implements Serializable {

    @ExcelProperty("账务类型")
    private String financeType;

    @ExcelProperty("入账时间")
    private String enterTime;

    @ExcelProperty("订单号")
    private String number;

    @ExcelProperty("微信业务单号")
    private String wxOrderId;

    @ExcelProperty("资金流向")
    private String capitalFlowType;

    @ExcelProperty("资金账户ID")
    private Long financeAccountId;

    @ExcelProperty("收支金额")
    private String amountDetail;

    @ExcelProperty("备注")
    private String description;
}
