package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "MinProdComboVO对象", description = "商品最低销售套餐实体")
public class MinProdComboDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品销售套餐ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("销售套餐ID")
    private Long id;

    @NotNull(message = "商品ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @NotBlank(message = "商品销售套餐名称不为空!")
    @ApiModelProperty(value = "商品销售套餐名称", required = true)
    private String comboTitle;

//    @NotBlank(message = "商品卖点不为空!")
    @ApiModelProperty(value = "商品卖点", required = true)
    private String sellPoint;

    @NotNull(message = "商品销售套餐金额不为空!")
    @ApiModelProperty(value = "商品销售套餐金额", required = true)
    private BigDecimal comboPrice;

    @NotNull(message = "商品套餐划线价不为空!")
    @ApiModelProperty(value = "商品套餐划线价", required = true)
    private BigDecimal comboStandardPrice;

    @NotNull(message = "商品成本价不为空!")
    @ApiModelProperty(value = "商品成本价", required = true)
    private BigDecimal comboCostPrice;

}