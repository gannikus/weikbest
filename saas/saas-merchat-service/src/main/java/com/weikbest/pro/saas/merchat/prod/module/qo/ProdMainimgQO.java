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
 * 商品详情页轮播图拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdMainimgQO对象", description = "商品详情页轮播图拆分多行表")
public class ProdMainimgQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("商品详情页轮播图")
    private String mainImg;


}