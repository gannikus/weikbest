package com.weikbest.pro.saas.merchat.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustomerVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoCustCouponRestrictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户领用优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustCouponRestrictPageVO对象", description = "客户领用优惠券记录分页查询")
public class CustCouponRestrictPageVO extends CustCouponRestrictVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("领取客户信息")
    private CustomerVO customerVO;

    @ApiModelProperty("订单信息")
    private OrderInfoCustCouponRestrictVO orderInfoCustCouponRestrictVO;
}