package com.weikbest.pro.saas.merchat.aftersale.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单售后表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleKeyGroupVO对象", description = "订单售后状态表")
public class OrderAfterSaleKeyGroupVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    private String afterSaleKey;

    @ApiModelProperty("数量")
    private Integer total;
}