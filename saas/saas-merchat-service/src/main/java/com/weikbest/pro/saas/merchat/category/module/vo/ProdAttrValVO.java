package com.weikbest.pro.saas.merchat.category.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品属性值表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAttrValVO对象", description = "商品属性值表（本期不用）")
public class ProdAttrValVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性ID")
    private Long attrId;

    @ApiModelProperty("属性值类型 1-文字")
    private String attrValType;

    @ApiModelProperty("属性值")
    private String attrVal;

    @ApiModelProperty("属性值排序")
    private Integer attrValOrd;


}