package com.weikbest.pro.saas.merchat.api.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author 甘之萌  2023/07/25 10:24
 */
@ApiModel
@Data
public class OrderInfoQo {

    @ApiModelProperty("单号列表")
    private List<String> numberList;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;
}
