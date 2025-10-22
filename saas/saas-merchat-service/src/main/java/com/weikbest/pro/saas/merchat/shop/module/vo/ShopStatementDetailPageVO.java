package com.weikbest.pro.saas.merchat.shop.module.vo;

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
 * 店铺对账单明细表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopStatementDetailVO对象", description = "店铺对账单明细表")
public class ShopStatementDetailPageVO extends ShopStatementDetailVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单id ")
    private Long orderId;

    @ApiModelProperty("订单来源 ")
    private String orderSource;

    @ApiModelProperty("下单路径 ")
    private String orderPath;

    @ApiModelProperty("订单状态 ")
    private String orderStatus;

    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    private String adAid;

    @ApiModelProperty("售后申请时间 yyyy-MM-dd HH:mm:ss")
    private Date orderAfterSaleApplyTime;


    @ApiModelProperty("买家姓名 ")
    private String customerName;

    @ApiModelProperty("收货人电话号码 ")
    private String customerPhone;
}