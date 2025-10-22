package com.weikbest.pro.saas.merchat.order.module.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/20
 * 批量发货excel对应实体
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BatchOrderDeliverDTO对象", description = "批量发货excel对应实体")
public class OrderBatchDeliverRecordExcelDTO {

    @ExcelIgnore
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ExcelProperty({"订单号"})
    private String orderNumber;

    @ExcelProperty({"快递公司"})
    private String logisticsCompanyName;

    @ExcelProperty({"快递单号"})
    private String courierNumber;

    @ExcelProperty({"发货结果"})
    private String result;

//    @ExcelIgnore
    @ExcelProperty({"备注"})
    private String remark;
}
