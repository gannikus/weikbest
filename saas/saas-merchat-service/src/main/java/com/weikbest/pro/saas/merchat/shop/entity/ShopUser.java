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
 * 商户店铺用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop_user")
@ApiModel(value = "ShopUser对象", description = "商户店铺用户表")
public class ShopUser implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String SHOP_ID = "shop_id";
    public static final String BUSINESS_USER_ID = "business_user_id";
    public static final String DATA_STATUS = "data_status";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户账户ID")
    @TableField("business_user_id")
    private Long businessUserId;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
