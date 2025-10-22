package com.weikbest.pro.saas.applet.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单收货地址拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderRecAddrVO对象", description = "订单收货地址拆分表")
public class AppOrderRecAddrVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("手机号 ")
    private String phone;

    @ApiModelProperty("收货地址 省、直辖市")
    private String addrProvince;

    @ApiModelProperty("收货地址 市")
    private String addrCity;

    @ApiModelProperty("收货地址 区、县")
    private String addrDistrict;

    @ApiModelProperty("详细地址 ")
    private String addr;


}