package com.weikbest.pro.saas.sysmerchat.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "PlatformCouponSceneDetailVO对象", description = "平台优惠券使用场景详情对象")
public class PlatformCouponSceneDetailVO extends PlatformCouponSceneVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券名称")
    private String name;
}