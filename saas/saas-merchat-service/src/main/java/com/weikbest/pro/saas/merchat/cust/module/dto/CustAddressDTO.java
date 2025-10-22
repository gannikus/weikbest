package com.weikbest.pro.saas.merchat.cust.module.dto;

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
 * 客户收货地址表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustAddressDTO对象", description = "客户收货地址表")
public class CustAddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "客户ID不为空!")
    @ApiModelProperty(value = "客户ID", required = true)
    private Long customerId;

    @NotBlank(message = "收货人不为空!")
    @ApiModelProperty(value = "收货人", required = true)
    private String consignee;

    @NotBlank(message = "收货手机号 不为空!")
    @ApiModelProperty(value = "收货手机号 ", required = true)
    private String consPhone;

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

    @NotBlank(message = "是否默认 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否默认 0-否 1-是", required = true)
    private String def;


}