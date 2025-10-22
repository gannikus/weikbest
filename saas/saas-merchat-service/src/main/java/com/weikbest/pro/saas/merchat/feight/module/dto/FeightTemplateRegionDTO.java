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
@ApiModel(value = "FeightTemplateRegionDTO对象", description = "运费模板可配送地区详情拆分多行表")
public class FeightTemplateRegionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "关联系统地区adcode不为空!")
    @ApiModelProperty(value = "关联系统地区adcode", required = true)
    private Integer regionAdcode;

    @NotBlank(message = "地区名称不为空!")
    @ApiModelProperty(value = "地区名称", required = true)
    private String regionName;
}