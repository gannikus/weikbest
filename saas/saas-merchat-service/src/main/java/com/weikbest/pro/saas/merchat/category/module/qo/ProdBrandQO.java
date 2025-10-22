package com.weikbest.pro.saas.merchat.category.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProdBrandQO对象", description = "商品品牌表（本期不用）")
public class ProdBrandQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("品牌编码")
    private String number;

    @ApiModelProperty("品牌名称")
    private String name;

    @ApiModelProperty("品牌logo")
    private String brandLogo;

    @ApiModelProperty("品牌排序")
    private Integer brandOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}