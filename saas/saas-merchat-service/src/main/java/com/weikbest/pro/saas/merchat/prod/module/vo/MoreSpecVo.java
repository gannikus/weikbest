package com.weikbest.pro.saas.merchat.prod.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "MoreSpecVo对象", description = "多规格套餐信息")
public class MoreSpecVo  implements Serializable {

    @ApiModelProperty("商品销售套餐值")
    private String specValue;

    @ApiModelProperty("售价")
    private BigDecimal comboPrice;

    @ApiModelProperty("划单价")
    private BigDecimal comboStandardPrice;

    @ApiModelProperty("成本价")
    private BigDecimal comboCostPrice;

    @ApiModelProperty("配图")
    private String img;

}
