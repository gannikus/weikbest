package com.weikbest.pro.saas.merchat.complaint.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单售后表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleKeyGroupVO对象", description = "订单售后状态表")
public class ComplaintBusiStatusGroupVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("投诉状态（商家处理）0-未处理 1-处理中 2-已处理")
    private String complaintBusiStatus;

    @ApiModelProperty("数量")
    private Integer total;
}