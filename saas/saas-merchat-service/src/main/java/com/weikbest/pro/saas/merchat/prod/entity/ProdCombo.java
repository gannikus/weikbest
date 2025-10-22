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
 * 商品销售套餐表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_combo")
@ApiModel(value = "ProdCombo对象", description = "商品销售套餐表")
public class ProdCombo implements Serializable {

    public static final String ID = "id";
    public static final String PROD_ID = "prod_id";
    public static final String SET_MEAL_TYPE = "set_meal_type";
    public static final String COMBO_TITLE = "combo_title";
    public static final String SELL_POINT = "sell_point";
    public static final String COMBO_PRICE = "combo_price";
    public static final String COMBO_STANDARD_PRICE = "combo_standard_price";
    public static final String COMBO_COST_PRICE = "combo_cost_price";
    public static final String COMBO_CODE = "combo_code";
    public static final String IMG = "img";
    public static final String COMBO_ORD = "combo_ord";
    public static final String FLAG = "flag";
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
    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    @TableField("set_meal_type")
    private Integer setMealType;

    @ApiModelProperty("商品销售套餐标题(多规格中规格名)")
    @TableField(value = "combo_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String comboTitle;
    @ApiModelProperty("商品卖点(多规格中规格值)")
    @TableField(value = "sell_point", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sellPoint;
    @ApiModelProperty("商品销售套餐金额(多规格中售价)")
    @TableField("combo_price")
    private BigDecimal comboPrice;
    @ApiModelProperty("商品套餐划线价(多规格中划单价)")
    @TableField("combo_standard_price")
    private BigDecimal comboStandardPrice;
    @ApiModelProperty("商品成本价(多规格中成本价)")
    @TableField("combo_cost_price")
    private BigDecimal comboCostPrice;

    @ApiModelProperty("套餐编码")
    @TableField("combo_code")
    private String comboCode;

    @ApiModelProperty("多规格中配图")
    @TableField("img")
    private String img;

    @ApiModelProperty("套餐排序")
    @TableField("combo_ord")
    private Integer comboOrd;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private String flag;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
