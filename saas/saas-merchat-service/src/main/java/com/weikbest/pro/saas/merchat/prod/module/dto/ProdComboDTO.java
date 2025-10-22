package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
@ApiModel(value = "ProdComboDTO对象", description = "商品销售套餐表")
public class ProdComboDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐Id", required = true)
    private Long id;

    @ApiModelProperty(value = "商品销售套餐标题(多规格中规格名)", required = true)
    private String comboTitle;

    @ApiModelProperty(value = "商品卖点(多规格中规格值)", required = true)
    private String sellPoint;

    @ApiModelProperty(value = "商品销售套餐金额(多规格中售价)", required = true)
    private BigDecimal comboPrice;

    @ApiModelProperty(value = "商品套餐划线价(多规格中划单价)", required = true)
    private BigDecimal comboStandardPrice;

    @ApiModelProperty(value = "商品成本价(多规格中成本价)", required = true)
    private BigDecimal comboCostPrice;

    @ApiModelProperty(value = "套餐编码", required = true)
    private String comboCode;

    @ApiModelProperty("多规格中配图")
    @TableField("img")
    private String img;

    @ApiModelProperty(value = "套餐排序", required = true)
    private Integer comboOrd;

    @ApiModelProperty(value = "商品销售套餐关联规格属性信息", required = true)
    private List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOList;

}