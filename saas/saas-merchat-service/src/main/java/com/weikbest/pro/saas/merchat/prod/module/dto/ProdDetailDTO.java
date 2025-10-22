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
 * 商品详情拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdDetailDTO对象", description = "商品详情拆分多行表")
public class ProdDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商品详情图不为空!")
    @ApiModelProperty(value = "商品详情图", required = true)
    private String detailImg;

}