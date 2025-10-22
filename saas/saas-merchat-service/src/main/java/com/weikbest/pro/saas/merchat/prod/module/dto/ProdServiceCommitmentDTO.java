package com.weikbest.pro.saas.merchat.prod.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * description
 *
 * @author 甘之萌  2023/03/22 16:39
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@ApiModel(value = "ProdServiceCommitmentDTO对象", description = "商品服务承诺表")
public class ProdServiceCommitmentDTO implements Serializable {


    @ApiModelProperty("承诺发货时间 1：当天发货 2：24小时 3：48小时 4：72小时")
    private Integer deliveryTimeService;

    @ApiModelProperty("承诺售后服务 1：急速售后 2：全国部分包邮 3：七天无理由退换 4：坏损包退 5：消费者保障服务 6：正品保证  7：假一赔十；多个用逗号分隔")
    private String afterSalesService;

    @ApiModelProperty("承诺其他服务，1：全场包邮；多个用逗号分隔")
    private String otherService;

    @ApiModelProperty("服务承诺")
    private List<String> serviceList;

}
