package com.weikbest.pro.saas.merchat.order.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "OrderBatchDeliverRecordDTO对象", description = "订单批量发货记录拆分表")
public class OrderBatchDeliverRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单ID不为空!")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "订单号不为空!")
    @ApiModelProperty(value = "订单号", required = true)
    private String orderNumber;

    @NotBlank(message = "导入状态 0-失败 1-成功不为空!")
    @ApiModelProperty(value = "导入状态 0-失败 1-成功", required = true)
    private String importStatus;

    @NotBlank(message = "物流公司不为空!")
    @ApiModelProperty(value = "物流公司", required = true)
    private String logisticsCompanyName;

    @NotBlank(message = "快递单号不为空!")
    @ApiModelProperty(value = "快递单号", required = true)
    private String courierNumber;


}