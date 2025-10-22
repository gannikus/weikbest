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
 * 平台优惠券使用场景表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponSceneDTO对象", description = "平台优惠券使用场景表")
public class PlatformCouponSceneDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "平台优惠券ID不为空!")
    @ApiModelProperty(value = "平台优惠券ID", required = true)
    private Long platformCouponId;

    @NotBlank(message = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取不为空!")
    @ApiModelProperty(value = "场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取", required = true)
    private String couponSceneType;


}