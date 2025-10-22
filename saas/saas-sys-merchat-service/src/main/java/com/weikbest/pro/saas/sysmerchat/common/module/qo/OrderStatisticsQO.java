package com.weikbest.pro.saas.sysmerchat.common.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "OrderStatisticsQO对象", description = "平台首页订单统计查询实体")
public class OrderStatisticsQO {

    @ApiModelProperty("是否按天统计 0-否 1-是")
    private String isDaySale;

    @ApiModelProperty("查询开始时间")
    private Date orderStartTime;

    @ApiModelProperty("查询结束时间")
    private Date orderEndTime;

    @ApiModelProperty("查询小程序ID，对应数据字典的 key")
    private String appId;
}
