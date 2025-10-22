package com.weikbest.pro.saas.sys.param.module.dto;

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
 * 平台行政区划表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RegionDTO对象", description = "平台行政区划表")
public class RegionDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "地区名称不为空!")
    @ApiModelProperty(value = "地区名称", required = true)
    private String name;

    @NotNull(message = "地区adcode不为空!")
    @ApiModelProperty(value = "地区adcode", required = true)
    private Integer adcode;

    @NotNull(message = "地区父adcode不为空!")
    @ApiModelProperty(value = "地区父adcode", required = true)
    private Integer parentAdcode;

    @NotBlank(message = "地区父名称不为空!")
    @ApiModelProperty(value = "地区父名称", required = true)
    private String parentName;

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal lng;

    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal lat;

    @NotBlank(message = "地区级别 province-省、自治区、直辖市 city-地级市、地区、自治州、盟 district-市辖区、县级市、县不为空!")
    @ApiModelProperty(value = "地区级别 province-省、自治区、直辖市 city-地级市、地区、自治州、盟 district-市辖区、县级市、县", required = true)
    private String regionLevel;


}