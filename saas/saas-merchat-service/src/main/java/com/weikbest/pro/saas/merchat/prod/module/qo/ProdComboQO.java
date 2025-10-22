package com.weikbest.pro.saas.merchat.prod.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品销售套餐表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdComboQO对象", description = "商品销售套餐表")
public class ProdComboQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty("商品销售套餐名称")
    private String comboTitle;

    @ApiModelProperty("商品卖点")
    private String sellPoint;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal comboPrice;

    @ApiModelProperty("商品套餐划线价")
    private BigDecimal comboStandardPrice;

    @ApiModelProperty("商品成本价")
    private BigDecimal comboCostPrice;

    @ApiModelProperty("套餐排序")
    private Integer comboOrd;

}