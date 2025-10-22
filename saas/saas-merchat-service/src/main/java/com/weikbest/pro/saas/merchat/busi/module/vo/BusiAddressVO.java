package com.weikbest.pro.saas.merchat.busi.module.vo;

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
@ApiModel(value = "BusiAddressVO对象", description = "商家详细地址信息表")
public class BusiAddressVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户地址ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @ApiModelProperty("联系人")
    private String name;

    @ApiModelProperty("联系方式")
    private String busiPhone;

    @ApiModelProperty("区号")
    private String busiAreaCode;

    @ApiModelProperty("座机号")
    private String busiLandlineNumber;

    @ApiModelProperty("分机号")
    private String busiExtensionNumber;

    @ApiModelProperty("省、直辖市")
    private String busiProvince;

    @ApiModelProperty("市")
    private String busiCity;

    @ApiModelProperty("区、县")
    private String busiDistrict;

    @ApiModelProperty("详细地址")
    private String addr;

    @ApiModelProperty("是否默认 0-否 1-是")
    private String def;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}