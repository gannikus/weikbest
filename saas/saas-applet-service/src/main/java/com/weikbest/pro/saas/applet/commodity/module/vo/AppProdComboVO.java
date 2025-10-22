package com.weikbest.pro.saas.applet.commodity.module.vo;

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
import java.util.List;

/**
 * <p>
 * 商品销售套餐表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdComboVO对象", description = "商品销售套餐表")
public class AppProdComboVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty("商品销售套餐标题(多规格中规格名)")
    private String comboTitle;

    @ApiModelProperty("商品卖点(多规格中规格值)")
    private String sellPoint;

    @ApiModelProperty("商品销售套餐金额(多规格中售价)")
    private BigDecimal comboPrice;

    @ApiModelProperty("商品套餐划线价(多规格中划单价)")
    private BigDecimal comboStandardPrice;

    @ApiModelProperty("商品成本价(多规格中成本价)")
    private BigDecimal comboCostPrice;

    @ApiModelProperty("多规格中配图")
    private String img;

    @ApiModelProperty("套餐规格值集")
    private List<AppProdComboAttrRelateVO> appProdComboAttrRelateVOS;

    @ApiModelProperty("实付金额")
    private BigDecimal paidInAmount;


}