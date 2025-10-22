package com.weikbest.pro.saas.merchat.prod.module.dto;

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
 * 商品与优惠券绑定拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdCouponRelateDTO对象", description = "商品与优惠券绑定拆分表")
public class ProdCouponRelateDTO implements Serializable {

    private static final long serialVersionUID = -5270335013017161293L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联优惠券ID ", required = true)
    private Long couponId;

    @ApiModelProperty(value = "优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券", required = true)
    private String couponType;


}