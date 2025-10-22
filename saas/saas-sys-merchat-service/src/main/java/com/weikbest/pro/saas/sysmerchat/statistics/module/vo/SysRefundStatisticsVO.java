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
@ApiModel(value = "SysRefundStatisticsVO对象", description = "平台退款统计显示实体")
public class SysRefundStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总支付单数")
    private Integer totalPayOrderNum;

    @ApiModelProperty("成功退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("成功退款订单数")
    private Integer refundOrderCount;

    @ApiModelProperty("广告流量成功退款人数")
    private Long advFlowRefundCount;

    @ApiModelProperty("自然流量成功退款人数")
    private Long naturalFlowRefundCount;

    @ApiModelProperty("自然流量成功退款人数")
    private Long platformFlowRefundCount;

    @ApiModelProperty("退款率")
    private BigDecimal refundRatio;
}
