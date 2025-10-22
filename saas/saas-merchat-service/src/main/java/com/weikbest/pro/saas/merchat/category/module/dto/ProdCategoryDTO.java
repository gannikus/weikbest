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
 * 商品分类表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdCategoryDTO对象", description = "商品分类表")
public class ProdCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "分类编码不为空!")
    @ApiModelProperty(value = "分类编码", required = true)
    private String number;

    @NotBlank(message = "分类名称不为空!")
    @ApiModelProperty(value = "分类名称", required = true)
    private String name;

//    @NotBlank(message = "分类图标不为空!")
//    @ApiModelProperty(value = "分类图标", required = true)
//    private String categoryIcon;
//
//    @JsonSerialize(using = ToStringSerializer.class)
//    @NotNull(message = "上级分类ID 不为空!")
//    @ApiModelProperty(value = "上级分类ID ", required = true)
//    private Long parentId;
//
//    @ApiModelProperty(value = "分类层级 最多2层", required = true)
//    private Integer categoryLevel;

    @ApiModelProperty(value = "分类排序", required = true)
    private Integer categoryOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}