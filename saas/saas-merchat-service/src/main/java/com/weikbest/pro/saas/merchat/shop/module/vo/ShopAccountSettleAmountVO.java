package com.weikbest.pro.saas.merchat.shop.module.vo;

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
 * @since :2022/12/25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopAccountSettleAmountVO对象", description = "店铺资金账户贷款账户待结算金额")
public class ShopAccountSettleAmountVO implements Serializable {

    @ApiModelProperty("待结算金额")
    private BigDecimal settleAmountTotal;

    @ApiModelProperty("待结算金额笔数")
    private Integer settleAmountCount;
}
