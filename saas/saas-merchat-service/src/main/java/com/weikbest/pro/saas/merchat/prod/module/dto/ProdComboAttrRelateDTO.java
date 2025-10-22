package com.weikbest.pro.saas.merchat.prod.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 商品销售套餐规格属性关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdComboAttrRelateDTO对象", description = "商品销售套餐规格属性关联表")
public class ProdComboAttrRelateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐属性Id", required = true)
    private Long id;

    @ApiModelProperty(value = "商品套餐规格属性名", required = true)
    private String attrName;

    @ApiModelProperty(value = "规格属性值集，多个值用 , 分割", required = true)
    private String attrValues;


}