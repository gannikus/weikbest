package com.weikbest.pro.saas.merchat.order.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "OrderRecAddrDTO对象", description = "订单收货地址拆分表")
public class OrderRecAddrDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @NotBlank(message = "收货人不为空!")
    @ApiModelProperty(value = "收货人", required = true)
    private String consignee;

    @NotBlank(message = "手机号 不为空!")
    @ApiModelProperty(value = "手机号 ", required = true)
    private String phone;

    @NotBlank(message = "收货地址 省、直辖市不为空!")
    @ApiModelProperty(value = "收货地址 省、直辖市", required = true)
    private String addrProvince;

    @NotBlank(message = "收货地址 市不为空!")
    @ApiModelProperty(value = "收货地址 市", required = true)
    private String addrCity;

    @NotBlank(message = "收货地址 区、县不为空!")
    @ApiModelProperty(value = "收货地址 区、县", required = true)
    private String addrDistrict;

    @NotBlank(message = "详细地址 不为空!")
    @ApiModelProperty(value = "详细地址 ", required = true)
    private String addr;

}