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
 * 商品销售属性表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdSkuQO对象", description = "商品销售属性表（本期不用）")
public class ProdSkuQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("库存ID ")
    private Long prodStockId;

    @ApiModelProperty("商品销售标题")
    private String skuTitle;

    @ApiModelProperty("商品卖点")
    private String sellPoint;

    @ApiModelProperty("关联规格属性ID集  逗号分隔")
    private String skuConnAttrIds;

    @ApiModelProperty("关联规格属性名集  逗号分隔")
    private String skuConnAttrNames;

    @ApiModelProperty("关联规格属性值集 逗号分隔")
    private String skuConnAttrValues;

    @ApiModelProperty("商品销售金额")
    private BigDecimal skuPrice;

    @ApiModelProperty("商品划线价")
    private BigDecimal skuStandardPrice;


}