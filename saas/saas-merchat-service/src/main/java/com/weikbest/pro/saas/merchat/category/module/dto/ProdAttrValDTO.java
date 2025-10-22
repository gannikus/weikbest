package com.weikbest.pro.saas.merchat.category.module.dto;

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
 * 商品属性值表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAttrValDTO对象", description = "商品属性值表（本期不用）")
public class ProdAttrValDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "属性ID不为空!")
    @ApiModelProperty(value = "属性ID", required = true)
    private Long attrId;

    @NotBlank(message = "属性值类型 1-文字不为空!")
    @ApiModelProperty(value = "属性值类型 1-文字", required = true)
    private String attrValType;

    @NotBlank(message = "属性值不为空!")
    @ApiModelProperty(value = "属性值", required = true)
    private String attrVal;

    @ApiModelProperty(value = "属性值排序", required = true)
    private Integer attrValOrd;


}