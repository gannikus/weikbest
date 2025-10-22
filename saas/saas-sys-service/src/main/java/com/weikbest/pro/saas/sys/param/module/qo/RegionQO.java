package com.weikbest.pro.saas.sys.param.module.qo;

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
@ApiModel(value = "RegionQO对象", description = "平台行政区划表")
public class RegionQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("地区名称")
    private String name;

    @ApiModelProperty("地区adcode")
    private Integer adcode;

    @ApiModelProperty("地区父adcode")
    private Long parentAdcode;

    @ApiModelProperty("地区父名称")
    private String parentName;

    @ApiModelProperty("经度")
    private BigDecimal lng;

    @ApiModelProperty("纬度")
    private BigDecimal lat;

    @ApiModelProperty("地区级别 province-省、自治区、直辖市 city-地级市、地区、自治州、盟 district-市辖区、县级市、县")
    private String regionLevel;


}