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
 * 小程序商品类目表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_applet_prod_category")
@ApiModel(value = "AppletProdCategory对象", description = "小程序商品类目表")
public class AppletProdCategory implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String ICON = "icon";
    public static final String PARENT_ID = "parent_id";
    public static final String CATEGORY_LEVEL = "category_level";
    public static final String CATEGORY_ORD = "category_ord";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("分类编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("分类名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("分类图标")
    @TableField(value = "icon", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String icon;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上级分类ID ")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("分类层级 最多2层")
    @TableField("category_level")
    private Integer categoryLevel;
    @ApiModelProperty("分类排序")
    @TableField("category_ord")
    private Integer categoryOrd;
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
