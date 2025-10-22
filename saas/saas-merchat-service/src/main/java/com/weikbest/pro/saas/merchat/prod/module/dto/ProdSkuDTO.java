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
@ApiModel(value = "ProdSkuDTO对象", description = "商品销售属性表（本期不用）")
public class ProdSkuDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品ID不为空!")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "库存ID 不为空!")
    @ApiModelProperty(value = "库存ID ", required = true)
    private Long prodStockId;

    @NotBlank(message = "商品销售标题不为空!")
    @ApiModelProperty(value = "商品销售标题", required = true)
    private String skuTitle;

//    @NotBlank(message = "商品卖点不为空!")
    @ApiModelProperty(value = "商品卖点", required = true)
    private String sellPoint;

    @NotBlank(message = "关联规格属性ID集  逗号分隔不为空!")
    @ApiModelProperty(value = "关联规格属性ID集  逗号分隔", required = true)
    private String skuConnAttrIds;

    @NotBlank(message = "关联规格属性名集  逗号分隔不为空!")
    @ApiModelProperty(value = "关联规格属性名集  逗号分隔", required = true)
    private String skuConnAttrNames;

    @NotBlank(message = "关联规格属性值集 逗号分隔不为空!")
    @ApiModelProperty(value = "关联规格属性值集 逗号分隔", required = true)
    private String skuConnAttrValues;

    @ApiModelProperty(value = "商品销售金额", required = true)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "商品划线价", required = true)
    private BigDecimal skuStandardPrice;


}