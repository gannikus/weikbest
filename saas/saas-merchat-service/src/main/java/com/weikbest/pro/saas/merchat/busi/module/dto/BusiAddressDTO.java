package com.weikbest.pro.saas.merchat.busi.module.dto;

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
 * 商家详细地址信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusiAddressDTO对象", description = "商家详细地址信息表")
public class BusiAddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @NotBlank(message = "联系人不为空!")
    @ApiModelProperty(value = "联系人", required = true)
    private String name;

    @NotBlank(message = "联系方式不为空!")
    @ApiModelProperty(value = "联系方式", required = true)
    private String busiPhone;

    @ApiModelProperty(value = "区号")
    private String busiAreaCode;

    @ApiModelProperty(value = "座机号")
    private String busiLandlineNumber;

    @ApiModelProperty(value = "分机号")
    private String busiExtensionNumber;

    @NotBlank(message = "省、直辖市不为空!")
    @ApiModelProperty(value = "省、直辖市", required = true)
    private String busiProvince;

    @NotBlank(message = "市不为空!")
    @ApiModelProperty(value = "市", required = true)
    private String busiCity;

    @NotBlank(message = "区、县不为空!")
    @ApiModelProperty(value = "区、县", required = true)
    private String busiDistrict;

    @NotBlank(message = "详细地址不为空!")
    @ApiModelProperty(value = "详细地址", required = true)
    private String addr;

    @NotBlank(message = "是否默认不为空!")
    @ApiModelProperty(value = "是否默认 0-否 1-是", required = true)
    private String def;
}