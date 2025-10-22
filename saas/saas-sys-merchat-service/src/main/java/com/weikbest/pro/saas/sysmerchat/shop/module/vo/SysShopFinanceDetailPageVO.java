package com.weikbest.pro.saas.sysmerchat.shop.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailVO;
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
@ApiModel(value = "SysShopFinanceDetailPageVO对象", description = "平台财务概况分页查询")
public class SysShopFinanceDetailPageVO extends ShopFinanceDetailVO {

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
