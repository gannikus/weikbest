package com.weikbest.pro.saas.merchat.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 店铺小程序关联关系表
 * @TableName t_mmdm_shop_applet_related
 */
@TableName(value ="t_mmdm_shop_applet_related")
@Data
@ApiModel(value = "ShopAppletRelated对象", description = "店铺小程序关联关系表")
public class ShopAppletRelated implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 店铺id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺id")
    @TableField(value = "shop_id")
    private Long shopId;

    /**
     * 小程序id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序id")
    @TableField(value = "applet_id")
    private Long appletId;

    /**
     * 创建时间
     */
    @ApiModelProperty("小程序id")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(value = "gmt_update ",fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
