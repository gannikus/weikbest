package com.weikbest.pro.saas.merchat.shop.entity;

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
 * 店铺访问表
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop_visit")
@ApiModel(value = "ShopVisit对象", description = "店铺访问表")
public class ShopVisit implements Serializable {

    public static final String ID = "id";
    public static final String SHOP_ID = "shop_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String PROD_ID = "prod_id";
    public static final String APP_ID = "app_id";
    public static final String VISIT_SHOP_PAGE = "visit_shop_page";
    public static final String VISIT_PROD_PAGE = "visit_prod_page";
    public static final String VISIT_TYPE = "visit_type";
    public static final String VISIT_TIME = "visit_time";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("关联小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("访问店铺页面URL")
    @TableField(value = "visit_shop_page", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String visitShopPage;
    @ApiModelProperty("访问商品页面URL")
    @TableField(value = "visit_prod_page", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String visitProdPage;
    @ApiModelProperty("访问类型 1-访问店铺 2-访问商品")
    @TableField(value = "visit_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String visitType;
    @ApiModelProperty("访问时间")
    @TableField("visit_time")
    private Date visitTime;
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
