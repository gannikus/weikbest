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
 * 商品样式拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdThemeDTO对象", description = "商品样式拆分表")
public class ProdThemeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商品展示图（缩略图）不为空!")
    @ApiModelProperty(value = "商品展示图（缩略图）", required = true)
    private String showImg;

    @ApiModelProperty(value = "商品详情页轮播图数量上限")
    private Integer mainNum;

    @NotBlank(message = "商品详情页轮播图比例不为空!")
    @ApiModelProperty(value = "商品详情页轮播图比例 1-1:1 2-3:4", required = true)
    private String mainRatioType;

    @ApiModelProperty(value = "商品详情图数量上限")
    private Integer detailNum;

    @NotBlank(message = "跑马灯样式不为空!")
    @ApiModelProperty(value = "跑马灯样式  1-淡入淡出浮层跑马灯 2-主图下方跑马灯", required = true)
    private String prodTheme;


}