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
@ApiModel(value = "OrderProdInfoDTO对象", description = "订单商品参数详细表")
public class OrderProdInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID不为空!")
    @ApiModelProperty(value = "关联商品ID", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "所属分类ID不为空!")
    @ApiModelProperty(value = "所属分类ID", required = true)
    private Long categoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "品牌ID不为空!")
    @ApiModelProperty(value = "品牌ID", required = true)
    private Long brandId;

    @ApiModelProperty(value = "数量", required = true)
    private Integer buyNumber;

    @NotBlank(message = "商品名称不为空!")
    @ApiModelProperty(value = "商品名称", required = true)
    private String prodName;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品销售属性ID不为空!")
    @ApiModelProperty(value = "关联商品销售属性ID", required = true)
    private Long prodSkuId;

    @NotBlank(message = "商品销售属性标题不为空!")
    @ApiModelProperty(value = "商品销售属性标题", required = true)
    private String prodSkuName;

    @NotBlank(message = "关联商品属性组合（JSON）不为空!")
    @ApiModelProperty(value = "关联商品属性组合（JSON）", required = true)
    private String prodSkuAttrValues;

    @ApiModelProperty(value = "商品销售金额", required = true)
    private BigDecimal prodSkuPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品库存ID不为空!")
    @ApiModelProperty(value = "关联商品库存ID", required = true)
    private Long prodStockId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品套餐ID不为空!")
    @ApiModelProperty(value = "商品套餐ID", required = true)
    private Long prodComboId;

    @NotBlank(message = "商品销售套餐名称不为空!")
    @ApiModelProperty(value = "商品销售套餐名称", required = true)
    private String prodComboTitle;

    @NotBlank(message = "商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}不为空!")
    @ApiModelProperty(value = "商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}", required = true)
    private String prodComboAttrValues;

    @ApiModelProperty(value = "商品销售套餐金额", required = true)
    private BigDecimal prodComboPrice;

    @ApiModelProperty(value = "商品套餐划线价", required = true)
    private BigDecimal prodComboStandardPrice;

    @ApiModelProperty(value = "商品成本价", required = true)
    private BigDecimal prodComboCostPrice;

    @NotBlank(message = "商品图片不为空!")
    @ApiModelProperty(value = "商品图片", required = true)
    private String prodImg;


}