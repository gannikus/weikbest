package com.weikbest.pro.saas.merchat.category.entity;

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
 * 小程序装修配置分录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_applet_dec_config_entry")
@ApiModel(value = "AppletDecConfigEntry对象", description = "小程序装修配置分录表")
public class AppletDecConfigEntry implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String APPLET_CONFIG_TYPE = "applet_config_type";
    public static final String PROD_ID = "prod_id";
    public static final String ENTRY_NAME = "entry_name";
    public static final String ENTRY_IMG = "entry_img";
    public static final String ENTRY_URL = "entry_url";
    public static final String ENTRY_ORD = "entry_ord";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序装修配置ID ")
    @TableField("id")
    private Long id;
    @ApiModelProperty("分录区域类型 1-轮播区 2-金刚区 3-魔方区 4-广告营销区")
    @TableField(value = "applet_config_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletConfigType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("名称")
    @TableField(value = "entry_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String entryName;
    @ApiModelProperty("图片")
    @TableField(value = "entry_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String entryImg;
    @ApiModelProperty("跳转链接")
    @TableField(value = "entry_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String entryUrl;
    @ApiModelProperty("顺序")
    @TableField("entry_ord")
    private Integer entryOrd;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
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
