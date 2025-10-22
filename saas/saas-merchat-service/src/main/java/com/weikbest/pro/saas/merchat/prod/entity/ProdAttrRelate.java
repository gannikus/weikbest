package com.weikbest.pro.saas.merchat.prod.entity;

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
 * 商品销售规格属性关联表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_attr_relate")
@ApiModel(value = "ProdAttrRelate对象", description = "商品销售规格属性关联表（本期不用）")
public class ProdAttrRelate implements Serializable {

    public static final String ID = "id";
    public static final String PROD_ID = "prod_id";
    public static final String ATTR_ID = "attr_id";
    public static final String ATTR_NAME = "attr_name";
    public static final String ATTR_VALUES = "attr_values";
    public static final String IS_VALUATION = "is_valuation";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    @TableField("prod_id")
    private Long prodId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品属性ID")
    @TableField("attr_id")
    private Long attrId;
    @ApiModelProperty("商品属性名")
    @TableField(value = "attr_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrName;
    @ApiModelProperty("规格属性值集，多个值用 , 分割")
    @TableField(value = "attr_values", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String attrValues;
    @ApiModelProperty("是否参与计价 0-否 1-是")
    @TableField(value = "is_valuation", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isValuation;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
