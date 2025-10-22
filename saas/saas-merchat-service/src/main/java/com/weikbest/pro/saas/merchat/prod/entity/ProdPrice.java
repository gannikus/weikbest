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
 * 商品价格信息拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_price")
@ApiModel(value = "ProdPrice对象", description = "商品价格信息拆分表")
public class ProdPrice implements Serializable {

    public static final String ID = "id";
    public static final String PROD_SKU_ID = "prod_sku_id";
    public static final String SKU_MIN_PRICE = "sku_min_price";
    public static final String SKU_MIN_STANDARD_PRICE = "sku_min_standard_price";
    public static final String SUBSIDY_PRICE = "subsidy_price";
    public static final String DISCOUNT_PRICE = "discount_price";
    public static final String PROD_COMBO_ID = "prod_combo_id";
    public static final String COMBO_MIN_PRICE = "combo_min_price";
    public static final String COMBO_MIN_STANDARD_PRICE = "combo_min_standard_price";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID（价格最低的SKU）")
    @TableField("prod_sku_id")
    private Long prodSkuId;
    @ApiModelProperty("商品销售金额（最低）")
    @TableField("sku_min_price")
    private BigDecimal skuMinPrice;
    @ApiModelProperty("商品划线价（最低）")
    @TableField("sku_min_standard_price")
    private BigDecimal skuMinStandardPrice;
    @ApiModelProperty("官方补贴金额")
    @TableField("subsidy_price")
    private BigDecimal subsidyPrice;
    @ApiModelProperty("是否开启支付失败弹框 0-否 1-是")
    @TableField("is_fail_pay_hint")
    private String isFailPayHint;
    @ApiModelProperty("支付优惠金额")
    @TableField("discount_price")
    private BigDecimal discountPrice;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品套餐ID（价格最低的套餐）")
    @TableField("prod_combo_id")
    private Long prodComboId;
    @ApiModelProperty("套餐商品销售金额（最低）")
    @TableField("combo_min_price")
    private BigDecimal comboMinPrice;
    @ApiModelProperty("套餐商品划线价（最低）")
    @TableField("combo_min_standard_price")
    private BigDecimal comboMinStandardPrice;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
