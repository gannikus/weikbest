package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderInfoDetailVO对象", description = "订单详情信息对象")
public class OrderInfoDetailVO extends OrderInfoVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("商家名称")
    private String businessName;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("小程序名称")
    private String appletName;

    @ApiModelProperty("客户信息")
    private OrderCustInfoVO orderCustInfoVO;

    @ApiModelProperty("收货信息")
    private OrderRecAddrVO orderRecAddrVO;

    @ApiModelProperty("物流信息")
    private OrderLogisticsVO orderLogisticsVO;

    @ApiModelProperty("商品信息")
    private OrderProdInfoVO orderProdInfoVO;

    @ApiModelProperty("订单支付记录信息")
    private OrderPayRecordVO orderPayRecordVO;

    @ApiModelProperty("订单售后信息")
    private OrderAfterSaleVO orderAfterSaleVO;

}