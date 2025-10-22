package com.weikbest.pro.saas.merchat.prod.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品价格信息拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdPriceDTO对象", description = "商品价格信息拆分表")
public class ProdPriceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "官方补贴金额")
    private BigDecimal subsidyPrice;

    @ApiModelProperty("是否开启支付失败弹框 0-否 1-是")
    private String isFailPayHint;

    @ApiModelProperty(value = "支付优惠金额")
    private BigDecimal discountPrice;


}