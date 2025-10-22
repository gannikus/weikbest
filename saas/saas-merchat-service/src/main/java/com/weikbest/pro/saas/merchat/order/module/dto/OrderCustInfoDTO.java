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
 * 客户订单与客户关联拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderCustInfoDTO对象", description = "客户订单与客户关联拆分表")
public class OrderCustInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @NotBlank(message = "客户姓名不为空!")
    @ApiModelProperty(value = "客户姓名", required = true)
    private String customerName;

    @NotBlank(message = "客户所在地区省、直辖市不为空!")
    @ApiModelProperty(value = "客户所在地区省、直辖市", required = true)
    private String customerProvince;

    @NotBlank(message = "客户所在地区市不为空!")
    @ApiModelProperty(value = "客户所在地区市", required = true)
    private String customerCity;

    @NotBlank(message = "客户所在地区区、县不为空!")
    @ApiModelProperty(value = "客户所在地区区、县", required = true)
    private String customerDistrict;

    @NotBlank(message = "详细地址不为空!")
    @ApiModelProperty(value = "详细地址", required = true)
    private String customerAddr;

    @NotBlank(message = "手机不为空!")
    @ApiModelProperty(value = "手机", required = true)
    private String customerPhone;

    @ApiModelProperty("客户备注")
    private String customerDescription;

    @NotBlank(message = "第三方平台类型 1-微信不为空!")
    @ApiModelProperty(value = "第三方平台类型 1-微信", required = true)
    private String tpType;

    @NotBlank(message = "昵称不为空!")
    @ApiModelProperty(value = "昵称", required = true)
    private String tpName;

    @NotBlank(message = "头像不为空!")
    @ApiModelProperty(value = "头像", required = true)
    private String tpPhoto;

    @NotBlank(message = "openid不为空!")
    @ApiModelProperty(value = "openid", required = true)
    private String tpOpenid;

    @NotBlank(message = "推广二维码不为空!")
    @ApiModelProperty(value = "推广二维码", required = true)
    private String tpQrcode;


}