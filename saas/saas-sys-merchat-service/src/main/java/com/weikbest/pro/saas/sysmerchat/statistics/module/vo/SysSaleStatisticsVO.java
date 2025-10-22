package com.weikbest.pro.saas.sysmerchat.statistics.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/4
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysSaleStatisticsVO对象", description = "平台销售数据显示实体")
public class SysSaleStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总支付金额")
    private BigDecimal totalPayAmount;

    @ApiModelProperty("订单数")
    private Integer totalOrderNum;

    @ApiModelProperty("支付订单数")
    private Integer totalPayOrderNum;

    @ApiModelProperty("下单用户数")
    private Long totalConversionCount;

    @ApiModelProperty("支付用户数")
    private Long totalPayCount;

    @ApiModelProperty("下单件数")
    private Integer totalConversionGoodsNum;

    @ApiModelProperty("支付件数")
    private Integer totalPayGoodsNum;

    @ApiModelProperty("客单价")
    private BigDecimal totalCustomerPayAmount;

}
