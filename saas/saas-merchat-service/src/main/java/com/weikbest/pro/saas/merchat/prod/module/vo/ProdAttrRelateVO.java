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
 * 商品销售规格属性关联表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAttrRelateVO对象", description = "商品销售规格属性关联表（本期不用）")
public class ProdAttrRelateVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品属性ID")
    private Long attrId;

    @ApiModelProperty("商品属性名")
    private String attrName;

    @ApiModelProperty("规格属性值集，多个值用 , 分割")
    private String attrValues;

    @ApiModelProperty("是否参与计价 0-否 1-是")
    private String isValuation;


}