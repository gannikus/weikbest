package com.weikbest.pro.saas.sysmerchat.coupon.module.dto;

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
 * 平台优惠券与适用商品拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponProdEntryDTO对象", description = "平台优惠券与适用商品拆分表")
public class PlatformCouponProdEntryDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID 不为空!")
    @ApiModelProperty(value = "关联商品ID ", required = true)
    private Long prodId;

    @NotBlank(message = "平台优惠券类型 3-平台劵不为空!")
    @ApiModelProperty(value = "平台优惠券类型 3-平台劵", required = true)
    private String sysCouponType;


}