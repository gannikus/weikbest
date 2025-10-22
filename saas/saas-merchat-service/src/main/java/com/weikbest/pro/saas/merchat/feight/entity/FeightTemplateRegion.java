package com.weikbest.pro.saas.merchat.feight.entity;

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
@TableName("t_mmdm_feight_template_region")
@ApiModel(value = "FeightTemplateRegion对象", description = "运费模板可配送地区详情拆分多行表")
public class FeightTemplateRegion implements Serializable {

    public static final String SUB_ENTRY_ID = "sub_entry_id";
    public static final String ID = "id";
    public static final String ENTRY_ID = "entry_id";
    public static final String REGION_ADCODE = "region_adcode";
    public static final String REGION_NAME = "region_name";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "sub_entry_id", type = IdType.ASSIGN_ID)
    private Long subEntryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板ID")
    @TableField("id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板详情ID")
    @TableField("entry_id")
    private Long entryId;
    @ApiModelProperty("关联系统地区adcode")
    @TableField("region_adcode")
    private Integer regionAdcode;
    @ApiModelProperty("地区名称")
    @TableField(value = "region_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String regionName;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
