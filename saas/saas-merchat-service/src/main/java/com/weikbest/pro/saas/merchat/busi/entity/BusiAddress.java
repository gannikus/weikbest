package com.weikbest.pro.saas.merchat.busi.entity;

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
import java.util.Date;

/**
 * <p>
 * 商家详细地址信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_busi_address")
@ApiModel(value = "BusiAddress对象", description = "商家详细地址信息表")
public class BusiAddress implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String NAME = "name";
    public static final String BUSI_PHONE = "busi_phone";
    public static final String BUSI_AREA_CODE = "busi_area_code";
    public static final String BUSI_LANDLINE_NUMBER = "busi_landline_number";
    public static final String BUSI_EXTENSION_NUMBER = "busi_extension_number";
    public static final String BUSI_PROVINCE = "busi_province";
    public static final String BUSI_CITY = "busi_city";
    public static final String BUSI_DISTRICT = "busi_district";
    public static final String ADDR = "addr";
    public static final String DEF = "def";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @ApiModelProperty("联系人")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("联系方式")
    @TableField(value = "busi_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiPhone;
    @ApiModelProperty("区号")
    @TableField(value = "busi_area_code", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiAreaCode;
    @ApiModelProperty("座机号")
    @TableField(value = "busi_landline_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiLandlineNumber;
    @ApiModelProperty("分机号")
    @TableField(value = "busi_extension_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiExtensionNumber;
    @ApiModelProperty("省、直辖市")
    @TableField(value = "busi_province", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiProvince;
    @ApiModelProperty("市")
    @TableField(value = "busi_city", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiCity;
    @ApiModelProperty("区、县")
    @TableField(value = "busi_district", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiDistrict;
    @ApiModelProperty("详细地址")
    @TableField(value = "addr", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addr;
    @ApiModelProperty("是否默认 0-否 1-是")
    @TableField(value = "def", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String def;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
