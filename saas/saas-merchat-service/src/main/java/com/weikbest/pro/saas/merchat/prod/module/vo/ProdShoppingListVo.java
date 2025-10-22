package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdShoppingListVo对象", description = "随手购绑定列表返回对象")
public class
ProdShoppingListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品图片")
    private String showImg;

    @ApiModelProperty("售价（元）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品内部备注")
    private String tips;

    @ApiModelProperty("商品排序")
    private Integer prodOrd;

    @ApiModelProperty("展示销量")
    private Integer sales;

    @ApiModelProperty("商品状态 1-上架中 2-已下架")
    private String prodStatus;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("商品卖点")
    private String prodSellingPoint;

    @ApiModelProperty(value = "商品类别 1- 自营商品 ，2-广告商品")
    private String goodsType;

    @ApiModelProperty(value = "商品套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty(value = "商品套餐数据")
    private List<ProdComboVO> comboVOS;

}
