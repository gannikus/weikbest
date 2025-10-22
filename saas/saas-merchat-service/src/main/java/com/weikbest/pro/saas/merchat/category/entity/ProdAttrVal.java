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
 * 商品属性值表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_attr_val")
@ApiModel(value = "ProdAttrVal对象", description = "商品属性值表（本期不用）")
public class ProdAttrVal implements Serializable {

    public static final String ID = "id";
    public static final String ATTR_ID = "attr_id";
    public static final String ATTR_VAL_TYPE = "attr_val_type";
    public static final String ATTR_VAL = "attr_val";
    public static final String ATTR_VAL_ORD = "attr_val_ord";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性ID")
    @TableField("attr_id")
    private Long attrId;
    @ApiModelProperty("属性值类型 1-文字")
    @TableField(value = "attr_val_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrValType;
    @ApiModelProperty("属性值")
    @TableField(value = "attr_val", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrVal;
    @ApiModelProperty("属性值排序")
    @TableField("attr_val_ord")
    private Integer attrValOrd;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
