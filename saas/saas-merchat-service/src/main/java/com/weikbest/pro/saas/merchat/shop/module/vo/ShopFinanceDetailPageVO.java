package com.weikbest.pro.saas.merchat.shop.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ShopFinanceDetailPageVO对象", description = "店铺资金明细分页对象")
public class ShopFinanceDetailPageVO extends ShopFinanceDetailVO {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("店铺电话")
    private String shopContact;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单id")
    private Long orderId;
}
