package com.weikbest.pro.saas.merchat.api.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author 甘之萌  2023/07/10 15:48
 */
@Data
@ApiModel(value ="OrderDeliveryErrorVo",description = "订单发货失败对象")
@Accessors(chain = true)
public class OrderDeliveryErrorVo {

    @ApiModelProperty("订单号")
    private String number;

    @ApiModelProperty("失败原因")
    private String reason;

}
