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
 * 商品品牌表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdBrandDTO对象", description = "商品品牌表（本期不用）")
public class ProdBrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "品牌编码不为空!")
    @ApiModelProperty(value = "品牌编码", required = true)
    private String number;

    @NotBlank(message = "品牌名称不为空!")
    @ApiModelProperty(value = "品牌名称", required = true)
    private String name;

    @NotBlank(message = "品牌logo不为空!")
    @ApiModelProperty(value = "品牌logo", required = true)
    private String brandLogo;

    @ApiModelProperty(value = "品牌排序", required = true)
    private Integer brandOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}