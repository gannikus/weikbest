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
@ApiModel(value = "ProdAttrRelateDTO对象", description = "商品销售规格属性关联表（本期不用）")
public class ProdAttrRelateDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品ID不为空!")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品属性ID不为空!")
    @ApiModelProperty(value = "商品属性ID", required = true)
    private Long attrId;

    @NotBlank(message = "商品属性名不为空!")
    @ApiModelProperty(value = "商品属性名", required = true)
    private String attrName;

    @NotBlank(message = "规格属性值集，多个值用 , 分割不为空!")
    @ApiModelProperty(value = "规格属性值集，多个值用 , 分割", required = true)
    private String attrValues;

    @NotBlank(message = "是否参与计价 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否参与计价 0-否 1-是", required = true)
    private String isValuation;


}