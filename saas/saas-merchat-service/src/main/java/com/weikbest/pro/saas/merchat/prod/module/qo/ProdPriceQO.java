package com.weikbest.pro.saas.merchat.prod.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@ApiModel(value = "ProdPriceQO对象", description = "商品价格信息拆分表")
public class ProdPriceQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID（价格最低的SKU）")
    private Long prodSkuId;

    @ApiModelProperty("商品销售金额（最低）")
    private BigDecimal skuMinPrice;

    @ApiModelProperty("商品划线价（最低）")
    private BigDecimal skuMinStandardPrice;

    @ApiModelProperty("官方补贴金额")
    private BigDecimal subsidyPrice;

    @ApiModelProperty("支付优惠金额")
    private BigDecimal discountPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品套餐ID（价格最低的套餐）")
    private Long prodComboId;

    @ApiModelProperty("套餐商品销售金额（最低）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("套餐商品划线价（最低）")
    private BigDecimal comboMinStandardPrice;


}