package com.weikbest.pro.saas.merchat.category.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProdAttrVO对象", description = "商品属性表（本期不用）")
public class ProdAttrVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("属性编码")
    private String number;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("属性图标")
    private String attrImg;

    @ApiModelProperty("属性排序")
    private Integer attrOrd;

    @ApiModelProperty("属性类型 字典项")
    private String attrType;

    @ApiModelProperty("属性值类型 1-文字")
    private String attrValType;

    @ApiModelProperty("可查询 0-否 1-是")
    private String isSearch;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}