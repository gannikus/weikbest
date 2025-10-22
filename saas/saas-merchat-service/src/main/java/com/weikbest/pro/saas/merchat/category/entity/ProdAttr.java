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
 * 商品属性表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_attr")
@ApiModel(value = "ProdAttr对象", description = "商品属性表（本期不用）")
public class ProdAttr implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String ATTR_IMG = "attr_img";
    public static final String ATTR_ORD = "attr_ord";
    public static final String ATTR_TYPE = "attr_type";
    public static final String ATTR_VAL_TYPE = "attr_val_type";
    public static final String IS_SEARCH = "is_search";
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
    @ApiModelProperty("属性编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("属性名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("属性图标")
    @TableField(value = "attr_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrImg;
    @ApiModelProperty("属性排序")
    @TableField("attr_ord")
    private Integer attrOrd;
    @ApiModelProperty("属性类型 字典项")
    @TableField(value = "attr_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrType;
    @ApiModelProperty("属性值类型 1-文字")
    @TableField(value = "attr_val_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrValType;
    @ApiModelProperty("可查询 0-否 1-是")
    @TableField(value = "is_search", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isSearch;
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
