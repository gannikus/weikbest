package com.weikbest.pro.saas.merchat.order.module.qo;

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
 * 订单商品参数详细表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-21
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderProdInfoQO对象", description = "订单商品参数详细表")
public class OrderProdInfoQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属分类ID")
    private Long categoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌ID")
    private Long brandId;

    @ApiModelProperty("数量")
    private Integer buyNumber;

    @ApiModelProperty("商品名称")
    private String prodName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品销售属性ID")
    private Long prodSkuId;

    @ApiModelProperty("商品销售属性标题")
    private String prodSkuName;

    @ApiModelProperty("关联商品属性组合（JSON）")
    private String prodSkuAttrValues;

    @ApiModelProperty("商品销售金额")
    private BigDecimal prodSkuPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品库存ID")
    private Long prodStockId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("商品销售套餐名称")
    private String prodComboTitle;

    @ApiModelProperty("商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal prodComboPrice;

    @ApiModelProperty("商品套餐划线价")
    private BigDecimal prodComboStandardPrice;

    @ApiModelProperty("商品成本价")
    private BigDecimal prodComboCostPrice;

    @ApiModelProperty("商品图片")
    private String prodImg;


}