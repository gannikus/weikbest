package com.weikbest.pro.saas.merchat.prod.module.vo;

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
@ApiModel(value = "ProdCouponRelateVO对象", description = "商品与优惠券绑定拆分表")
public class ProdCouponRelateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联优惠券ID ")
    private Long couponId;

    @ApiModelProperty("优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券")
    private String couponType;


}