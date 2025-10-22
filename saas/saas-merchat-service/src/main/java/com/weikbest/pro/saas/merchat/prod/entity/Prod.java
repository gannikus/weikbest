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
 * 商品基本信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod")
@ApiModel(value = "Prod对象", description = "商品基本信息表")
public class Prod implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String SHOP_ID = "shop_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String BRAND_ID = "brand_id";
    public static final String GOODS_TYPE = "goods_type";
    public static final String SET_MEAL_TYPE = "set_meal_type";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String TIPS = "tips";
    public static final String PROD_ORD = "prod_ord";
    public static final String SALES = "sales";
    public static final String SAFEGUARD = "safeguard";
    public static final String PROD_STATUS = "prod_status";
    public static final String JOIN_SHOP_MAINPAGE = "join_shop_mainpage";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String FLAG = "flag";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String SHOPPING_PROD_ID = "shopping_prod_id";
    public static final String SHOPPING_COMBO_ID = "shopping_combo_id";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("类目ID")
    @TableField("category_id")
    private Long categoryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌ID")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty("商品卖点")
    @TableField(value = "prod_selling_point",insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodSellingPoint;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品类别 1- 自营商品 ，2-广告商品")
    @TableField("goods_type")
    private Integer goodsType;

    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty("商品编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("商品名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("商品内部备注")
    @TableField(value = "tips", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tips;
    @ApiModelProperty("商品排序")
    @TableField("prod_ord")
    private Integer prodOrd;
    @ApiModelProperty("展示销量")
    @TableField("sales")
    private Integer sales;
    @ApiModelProperty("隐私协议 0-不展示 1-展示")
    @TableField(value = "safeguard", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String safeguard;
    @ApiModelProperty("商品状态 1-上架中 2-已下架")
    @TableField(value = "prod_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodStatus;
    @ApiModelProperty("加入店铺首页 0-关闭 1-已加入")
    @TableField(value = "join_shop_mainpage", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinShopMainpage;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private String flag;
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

    @ApiModelProperty("支付类型 ,1-微信支付，2-货到付款")
    @TableField(value = "pay_type")
    private String payType;

    @ApiModelProperty("是否开启到付验证开关 0-关闭，1-开启")
    @TableField(value = "is_open_cash_on_delivery_sms")
    private Integer isOpenCashOnDeliverySms;

    @ApiModelProperty("随手购商品Id")
    @TableField(value = "shopping_prod_id")
    private Long shoppingProdId;

    @ApiModelProperty("随手购商品套餐Id")
    @TableField(value = "shopping_combo_id")
    private Long shoppingComboId;

    @ApiModelProperty("是否开启限购 0-关闭，1-开启")
    @TableField(value = "is_open_order_limit")
    private String isOpenOrderLimit;

    @ApiModelProperty("客服开关，0-关闭，1-开启")
    @TableField(value = "customer_service_switch")
    private String customerServiceSwitch;


}
