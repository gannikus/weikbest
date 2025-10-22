package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单批量发货记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderBatchDeliverListVO对象", description = "订单批量发货记录列表对象")
public class OrderBatchDeliverListVO extends OrderBatchDeliverVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("操作人名称")
    private String operatorName;
}