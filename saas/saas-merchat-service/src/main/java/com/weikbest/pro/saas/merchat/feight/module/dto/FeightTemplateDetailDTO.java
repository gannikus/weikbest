package com.weikbest.pro.saas.merchat.feight.module.dto;

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
import java.util.List;

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
@ApiModel(value = "FeightTemplateDetailDTO对象", description = "运费模板详情拆分多行表")
public class FeightTemplateDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "首件(个)", required = true)
    private Integer firstPiece;

    @ApiModelProperty(value = "首件金额(元)", required = true)
    private BigDecimal firstAmount;

    @ApiModelProperty(value = "续件(个)", required = true)
    private Integer keepPiece;

    @ApiModelProperty(value = "续件金额(元)", required = true)
    private BigDecimal keepAmount;

    @NotBlank(message = "是否指定包邮条件不为空!")
    @ApiModelProperty(value = "是否指定包邮条件 0-否 1-是", required = true)
    private String isPinkage;

    @ApiModelProperty(value = "满包邮数量")
    private Integer pinkageNum;

    @NotBlank(message = "包邮条件类型")
    @ApiModelProperty(value = "包邮条件类型 1-元 2-件")
    private String pinkageType;

    @NotNull(message = "运费模板可配送地区列表不为空！")
    @ApiModelProperty(value = "运费模板可配送地区列表", required = true)
    private List<FeightTemplateRegionDTO> feightTemplateRegionDTOList;

}