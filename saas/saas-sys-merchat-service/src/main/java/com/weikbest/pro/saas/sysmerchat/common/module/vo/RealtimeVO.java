package com.weikbest.pro.saas.sysmerchat.common.module.vo;

import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RealtimeVO对象", description = "平台首页实时概况实体")
public class RealtimeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("今日概览")
    private DayRealtimeVO todayRealtimeVO;

    @ApiModelProperty("昨日概览")
    private DayRealtimeVO yesterdayRealtimeVO;

    @ApiModelProperty("总概览")
    private DayRealtimeVO totalRealtimeVO;

    @ApiModelProperty("销售额日环比")
    private BigDecimal saleAmountDailyCycle;

    @ApiModelProperty("订单数日环比")
    private BigDecimal orderNumDailyCycle;

    @ApiModelProperty("支付人数日环比")
    private BigDecimal payCountDailyCycle;

    @ApiModelProperty("支付件数日环比")
    private BigDecimal payGoodsNumDailyCycle;

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "DayRealtimeVO对象", description = "日实时概况")
    public static class DayRealtimeVO {

        @ApiModelProperty("销售额(元)")
        private BigDecimal saleAmount;

        @ApiModelProperty("订单数(单)")
        private Integer orderNum;

        @ApiModelProperty("支付人数")
        private Long payCount;

        @ApiModelProperty("支付件数")
        private Integer payGoodsNum;
    }

}
