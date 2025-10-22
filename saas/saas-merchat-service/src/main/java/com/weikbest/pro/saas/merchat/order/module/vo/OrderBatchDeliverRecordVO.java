package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单批量发货记录拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderBatchDeliverRecordVO对象", description = "订单批量发货记录拆分表")
public class OrderBatchDeliverRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单批量发货ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("导入状态 0-失败 1-成功")
    private String importStatus;

    @ApiModelProperty("物流公司")
    private String logisticsCompanyName;

    @ApiModelProperty("快递单号")
    private String courierNumber;


}