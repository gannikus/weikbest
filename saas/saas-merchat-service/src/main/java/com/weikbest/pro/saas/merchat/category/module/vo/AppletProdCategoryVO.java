package com.weikbest.pro.saas.merchat.category.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 小程序商品类目表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletProdCategoryVO对象", description = "小程序商品类目表")
public class AppletProdCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("分类ID ")
    private Long id;

    @ApiModelProperty("分类编码")
    private String number;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类图标")
    private String icon;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上级分类ID ")
    private Long parentId;

    @ApiModelProperty("分类层级 最多2层")
    private Integer categoryLevel;

    @ApiModelProperty("分类排序")
    private Integer categoryOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("子分类数据集")
    private List<AppletProdCategoryVO> children = new ArrayList<>();

}