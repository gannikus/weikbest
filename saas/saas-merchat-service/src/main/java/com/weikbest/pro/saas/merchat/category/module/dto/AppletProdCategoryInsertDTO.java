package com.weikbest.pro.saas.merchat.category.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "AppletProdCategoryInsertDTO对象", description = "小程序商品类目新增实体")
public class AppletProdCategoryInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "分类编码不为空!")
    @ApiModelProperty(value = "分类编码", required = true)
    private String number;

    @NotBlank(message = "分类名称不为空!")
    @ApiModelProperty(value = "分类名称", required = true)
    private String name;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @NotNull(message = "分类层级 不为空!")
    @ApiModelProperty(value = "分类层级 最多2层", required = true)
    private Integer categoryLevel;

    @ApiModelProperty(value = "分类排序")
    private Integer categoryOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("子分类数据集")
    private List<AppletProdCategoryInsertDTO> children = new ArrayList<>();


}