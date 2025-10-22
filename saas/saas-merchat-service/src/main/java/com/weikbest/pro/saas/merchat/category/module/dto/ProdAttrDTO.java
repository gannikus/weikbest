package com.weikbest.pro.saas.merchat.category.module.dto;

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
 * 商品属性表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAttrDTO对象", description = "商品属性表（本期不用）")
public class ProdAttrDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "属性编码不为空!")
    @ApiModelProperty(value = "属性编码", required = true)
    private String number;

    @NotBlank(message = "属性名称不为空!")
    @ApiModelProperty(value = "属性名称", required = true)
    private String name;

    @NotBlank(message = "属性图标不为空!")
    @ApiModelProperty(value = "属性图标", required = true)
    private String attrImg;

    @ApiModelProperty(value = "属性排序", required = true)
    private Integer attrOrd;

    @NotBlank(message = "属性类型 字典项不为空!")
    @ApiModelProperty(value = "属性类型 字典项", required = true)
    private String attrType;

    @NotBlank(message = "属性值类型 1-文字不为空!")
    @ApiModelProperty(value = "属性值类型 1-文字", required = true)
    private String attrValType;

    @NotBlank(message = "可查询 0-否 1-是不为空!")
    @ApiModelProperty(value = "可查询 0-否 1-是", required = true)
    private String isSearch;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}