package com.weikbest.pro.saas.merchat.order.entity;

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
 * 订单商品参数详细表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-21
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_prod_info")
@ApiModel(value = "OrderProdInfo对象", description = "订单商品参数详细表")
public class OrderProdInfo implements Serializable {

    public static final String ID = "id";
    public static final String PROD_ID = "prod_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String BRAND_ID = "brand_id";
    public static final String BUY_NUMBER = "buy_number";
    public static final String PROD_NAME = "prod_name";
    public static final String TIPS = "tips";
    public static final String PROD_SKU_ID = "prod_sku_id";
    public static final String PROD_SKU_NAME = "prod_sku_name";
    public static final String PROD_SKU_ATTR_VALUES = "prod_sku_attr_values";
    public static final String PROD_SKU_PRICE = "prod_sku_price";
    public static final String PROD_STOCK_ID = "prod_stock_id";
    public static final String PROD_COMBO_ID = "prod_combo_id";
    public static final String PROD_COMBO_TITLE = "prod_combo_title";
    public static final String PROD_COMBO_ATTR_VALUES = "prod_combo_attr_values";
    public static final String PROD_COMBO_PRICE = "prod_combo_price";
    public static final String PROD_COMBO_STANDARD_PRICE = "prod_combo_standard_price";
    public static final String COMBO_CODE = "combo_code";
    public static final String PROD_COMBO_COST_PRICE = "prod_combo_cost_price";
    public static final String PROD_IMG = "prod_img";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    @TableField("prod_id")
    private Long prodId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属分类ID")
    @TableField("category_id")
    private Long categoryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌ID")
    @TableField("brand_id")
    private Long brandId;
    @ApiModelProperty("数量")
    @TableField("buy_number")
    private Integer buyNumber;
    @ApiModelProperty("商品名称")
    @TableField(value = "prod_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodName;
    @ApiModelProperty("商品内部备注")
    @TableField(value = "tips", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tips;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品销售属性ID")
    @TableField("prod_sku_id")
    private Long prodSkuId;
    @ApiModelProperty("商品销售属性标题")
    @TableField(value = "prod_sku_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodSkuName;
    @ApiModelProperty("关联商品属性组合（JSON）")
    @TableField(value = "prod_sku_attr_values", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodSkuAttrValues;
    @ApiModelProperty("商品销售金额")
    @TableField("prod_sku_price")
    private BigDecimal prodSkuPrice;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品库存ID")
    @TableField("prod_stock_id")
    private Long prodStockId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    @TableField("prod_combo_id")
    private Long prodComboId;
    @ApiModelProperty("商品销售套餐名称")
    @TableField(value = "prod_combo_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodComboTitle;
    @ApiModelProperty("商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}")
    @TableField(value = "prod_combo_attr_values", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodComboAttrValues;
    @ApiModelProperty("商品销售套餐金额")
    @TableField("prod_combo_price")
    private BigDecimal prodComboPrice;
    @ApiModelProperty("商品套餐划线价")
    @TableField("prod_combo_standard_price")
    private BigDecimal prodComboStandardPrice;
    @ApiModelProperty("商品套餐编码")
    @TableField("combo_code")
    private String comboCode;
    @ApiModelProperty("商品成本价")
    @TableField("prod_combo_cost_price")
    private BigDecimal prodComboCostPrice;
    @ApiModelProperty("商品图片")
    @TableField(value = "prod_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodImg;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
