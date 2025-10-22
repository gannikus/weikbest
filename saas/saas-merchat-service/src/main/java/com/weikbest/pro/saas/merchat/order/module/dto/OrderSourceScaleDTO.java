package com.weikbest.pro.saas.merchat.order.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单来源分账比例表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderSourceScaleDTO对象", description = "订单来源分账比例表")
public class OrderSourceScaleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "订单来源 不为空!")
    @ApiModelProperty(value = "订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量", required = true)
    private String orderSource;

    @NotBlank(message = "下单路径不为空!")
    @ApiModelProperty(value = "下单路径", required = true)
    private String orderPath;

    @ApiModelProperty(value = "商户分账比例", required = true)
    private BigDecimal businessScale;

    @ApiModelProperty(value = "平台分账比例", required = true)
    private BigDecimal platformScale;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}