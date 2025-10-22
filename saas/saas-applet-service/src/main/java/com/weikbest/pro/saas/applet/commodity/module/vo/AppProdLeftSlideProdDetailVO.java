package com.weikbest.pro.saas.applet.commodity.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 商品基本信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdVO对象", description = "商品基本信息表")
public class AppProdLeftSlideProdDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long id;

    @ApiModelProperty("商品展示图（缩略图）")
    private String showImg;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品短标题")
    private String tips;

    @ApiModelProperty("展示销量")
    private Integer sales;

    @ApiModelProperty("关联商品套餐ID（价格最低的套餐）")
    private Long prodComboId;

    @ApiModelProperty("套餐商品销售金额（最低）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("套餐商品划线价（最低）")
    private BigDecimal comboMinStandardPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

}