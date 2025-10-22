package com.weikbest.pro.saas.merchat.feight.module.qo;

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
 * 运费模板详情拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "FeightTemplateDetailQO对象", description = "运费模板详情拆分多行表")
public class FeightTemplateDetailQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("首件(个)")
    private Integer firstPiece;

    @ApiModelProperty("首件金额(元)")
    private BigDecimal firstAmount;

    @ApiModelProperty("续件(个)")
    private Integer keepPiece;

    @ApiModelProperty("续件金额(元)")
    private BigDecimal keepAmount;

    @ApiModelProperty("是否指定包邮条件 0-否 1-是")
    private String isPinkage;

    @ApiModelProperty("满包邮数量")
    private Integer pinkageNum;

    @ApiModelProperty("包邮条件类型 1-元 2-件")
    private String pinkageType;


}