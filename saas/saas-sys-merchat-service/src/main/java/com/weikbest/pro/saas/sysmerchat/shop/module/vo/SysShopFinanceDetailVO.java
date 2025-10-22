package com.weikbest.pro.saas.sysmerchat.shop.module.vo;

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
 * @since :2022/12/24
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysShopFinanceDetailVO对象", description = "平台财务概况")
public class SysShopFinanceDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("支付总金额（元）")
    private BigDecimal payTotalAmount;

    @ApiModelProperty("店铺已结算总金额（元）")
    private BigDecimal settleTotalAmount;

    @ApiModelProperty("平台押账总金额（元）")
    private BigDecimal depositAccountTotalAmount;

    @ApiModelProperty("退款总金额（元）")
    private BigDecimal refundTotalAmount;

    @ApiModelProperty("平台总抽佣（元）")
    private BigDecimal commissionTotalAmount;
}
