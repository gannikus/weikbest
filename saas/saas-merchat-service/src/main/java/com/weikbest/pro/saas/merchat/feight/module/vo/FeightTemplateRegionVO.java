package com.weikbest.pro.saas.merchat.feight.module.vo;

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
 * 运费模板可配送地区详情拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "FeightTemplateRegionVO对象", description = "运费模板可配送地区详情拆分多行表")
public class FeightTemplateRegionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板详情ID")
    private Long entryId;

    @ApiModelProperty("关联系统地区adcode")
    private Integer regionAdcode;

    @ApiModelProperty("地区名称")
    private String regionName;

}