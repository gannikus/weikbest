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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品销售属性表（本期不用）
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_sku")
@ApiModel(value = "ProdSku对象", description = "商品销售属性表（本期不用）")
public class ProdSku implements Serializable {

    public static final String ID = "id";
    public static final String PROD_ID = "prod_id";
    public static final String PROD_STOCK_ID = "prod_stock_id";
    public static final String SKU_TITLE = "sku_title";
    public static final String SELL_POINT = "sell_point";
    public static final String SKU_CONN_ATTR_IDS = "sku_conn_attr_ids";
    public static final String SKU_CONN_ATTR_NAMES = "sku_conn_attr_names";
    public static final String SKU_CONN_ATTR_VALUES = "sku_conn_attr_values";
    public static final String SKU_PRICE = "sku_price";
    public static final String SKU_STANDARD_PRICE = "sku_standard_price";
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
    @ApiModelProperty("库存ID ")
    @TableField("prod_stock_id")
    private Long prodStockId;
    @ApiModelProperty("商品销售标题")
    @TableField(value = "sku_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String skuTitle;
    @ApiModelProperty("商品卖点")
    @TableField(value = "sell_point", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sellPoint;
    @ApiModelProperty("关联规格属性ID集  逗号分隔")
    @TableField(value = "sku_conn_attr_ids", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String skuConnAttrIds;
    @ApiModelProperty("关联规格属性名集  逗号分隔")
    @TableField(value = "sku_conn_attr_names", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String skuConnAttrNames;
    @ApiModelProperty("关联规格属性值集 逗号分隔")
    @TableField(value = "sku_conn_attr_values", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String skuConnAttrValues;
    @ApiModelProperty("商品销售金额")
    @TableField("sku_price")
    private BigDecimal skuPrice;
    @ApiModelProperty("商品划线价")
    @TableField("sku_standard_price")
    private BigDecimal skuStandardPrice;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
