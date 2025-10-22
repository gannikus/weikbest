package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
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
import java.util.Date;

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
@TableName("t_sys_region")
@ApiModel(value = "Region对象", description = "平台行政区划表")
public class Region implements Serializable {

    public static final String ID = "id";
    public static final String ADCODE = "adcode";
    public static final String NAME = "name";
    public static final String PARENT_ADCODE = "parent_adcode";
    public static final String PARENT_NAME = "parent_name";
    public static final String LNG = "lng";
    public static final String LAT = "lat";
    public static final String REGION_LEVEL = "region_level";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("地区adcode")
    @TableField("adcode")
    private Integer adcode;
    @ApiModelProperty("地区名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("地区父adcode")
    @TableField("parent_adcode")
    private Integer parentAdcode;
    @ApiModelProperty("地区父名称")
    @TableField(value = "parent_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String parentName;
    @ApiModelProperty("经度")
    @TableField("lng")
    private BigDecimal lng;
    @ApiModelProperty("纬度")
    @TableField("lat")
    private BigDecimal lat;
    @ApiModelProperty("地区级别 province-省、自治区、直辖市 city-地级市、地区、自治州、盟 district-市辖区、县级市、县")
    @TableField(value = "region_level", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String regionLevel;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
