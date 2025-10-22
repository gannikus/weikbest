package com.weikbest.pro.saas.applet.comm.module.vo;

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
 * 商品营销分类表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppProdSemCategoryVO对象", description = "商品营销分类表")
public class AppProdSemCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主健ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联营销位位置ID")
    private Long semPositionId;

    @ApiModelProperty("分类编码")
    private String number;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类图标")
    private String semCategoryIcon;

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


}
