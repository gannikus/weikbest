package com.weikbest.pro.saas.sysmerchat.common.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
@ApiModel(value = "OrderStatisticsVO对象", description = "平台订单统计实体")
public class OrderStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("x轴数据")
    private List<String> xAxisData;

    @ApiModelProperty("查询数据")
    private List<BigDecimal> seriesData;
}
