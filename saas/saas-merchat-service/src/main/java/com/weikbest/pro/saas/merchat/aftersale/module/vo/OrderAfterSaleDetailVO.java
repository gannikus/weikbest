package com.weikbest.pro.saas.merchat.aftersale.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiAddressVO;
import com.weikbest.pro.saas.merchat.order.module.vo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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
@ApiModel(value = "OrderAfterSaleDetailVO对象", description = "订单售后详情表")
public class OrderAfterSaleDetailVO extends OrderAfterSaleVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后单ID")
    private Long id;

    @ApiModelProperty("售后单过期时间")
    private Date timeout;

    @ApiModelProperty("商户店铺名称")
    private String shopName;

    @ApiModelProperty("订单信息")
    private OrderInfoVO orderInfoVO;

    @ApiModelProperty("商品信息")
    private OrderProdInfoVO orderProdInfoVO;

    @ApiModelProperty("订单客户信息")
    private OrderCustInfoVO orderCustInfoVO;

    @ApiModelProperty("订单支付记录信息")
    private OrderPayRecordVO orderPayRecordVO;

    @ApiModelProperty("客户收货地址信息")
    private OrderRecAddrVO orderRecAddrVO;

    @ApiModelProperty("协商记录")
    private List<OrderAfterSaleConsultRecordDetailVO> orderAfterSaleConsultRecordDetailVOList;

    @ApiModelProperty("售后图片")
    private List<String> imglist;

    @ApiModelProperty("退货地址")
    private String backAddress;

    @ApiModelProperty("商户地址信息")
    private List<BusiAddressVO> busiAddressList;
}
