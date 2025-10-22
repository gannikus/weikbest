package com.weikbest.pro.saas.merchat.order.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "OrderStatusGroupVO对象", description = "订单状态表")
public class OrderStatusGroupVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭 15-24小时待发货状态 40-发货已超时 60-售后中")
    private String orderGroupStatus;

    @ApiModelProperty("数量")
    private Integer total;
}