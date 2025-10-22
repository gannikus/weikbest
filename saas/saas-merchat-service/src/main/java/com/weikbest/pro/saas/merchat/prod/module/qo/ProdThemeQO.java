package com.weikbest.pro.saas.merchat.prod.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProdThemeQO对象", description = "商品样式拆分表")
public class ProdThemeQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("商品展示图（缩略图）")
    private String showImg;

    @ApiModelProperty("商品详情页轮播图数量上限")
    private Integer mainNum;

    @ApiModelProperty("商品详情页轮播图比例 1-1:1 2-3:4")
    private String mainRatioType;

    @ApiModelProperty("商品详情图数量上限")
    private Integer detailNum;

    @ApiModelProperty("跑马灯样式  1-淡入淡出浮层跑马灯 2-主图下方跑马灯")
    private String prodTheme;


}