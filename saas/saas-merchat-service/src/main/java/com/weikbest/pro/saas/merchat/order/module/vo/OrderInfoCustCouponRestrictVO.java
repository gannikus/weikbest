package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

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
@ApiModel(value = "OrderInfoCustCouponRestrictVO对象", description = "订单关联用户领用优惠券对象")
public class OrderInfoCustCouponRestrictVO extends OrderInfoVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /** 其他附表信息 */
    /**
     * 商品信息
     */
    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品销售套餐名称")
    private String prodComboTitle;

    @ApiModelProperty("商品图片")
    private String prodImg;

}