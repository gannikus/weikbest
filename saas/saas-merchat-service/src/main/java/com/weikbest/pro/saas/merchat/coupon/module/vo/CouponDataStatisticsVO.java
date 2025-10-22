package com.weikbest.pro.saas.merchat.coupon.module.vo;

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
 * @since :2022/11/12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CouponDataStatisticsVO对象", description = "优惠券数据统计实体")
public class CouponDataStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总领取量 ")
    private Integer totalCount;

    @ApiModelProperty("用券核销 ")
    private Integer useCouponCount;

    @ApiModelProperty("主动核销 ")
    private Integer initiativeCouponCount;

    @ApiModelProperty("剩余量 ")
    private Integer surplusCount;

    @ApiModelProperty("使用率% ")
    private BigDecimal usageRate;

    @ApiModelProperty("今日领取量 ")
    private Integer todayCount;

    @ApiModelProperty("今日使用量 ")
    private Integer todayUseCount;

    @ApiModelProperty("支付订单数 ")
    private Integer payOrderCount;

    @ApiModelProperty("支付总金额（元） ")
    private BigDecimal payAmountTotal;
}
