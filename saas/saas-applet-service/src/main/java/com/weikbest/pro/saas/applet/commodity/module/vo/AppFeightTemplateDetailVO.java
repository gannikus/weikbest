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
@ApiModel(value = "FeightTemplateDetailVO对象", description = "运费模板详情拆分多行表")
public class AppFeightTemplateDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板ID ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板详情ID")
    private Long entryId;

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

    @ApiModelProperty("具体可配送地区数据集")
    private List<AppFeightTemplateRegionVO> appFeightTemplateRegionVOList;

}