package com.weikbest.pro.saas.merchat.prod.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商品限购设置表
 * @TableName t_mmdm_prod_purchase_restrictions
 */
@TableName(value ="t_mmdm_prod_purchase_restrictions")
@Data
@Accessors(chain = true)
@ApiModel(value = "ProdPurchaseRestrictions对象", description = "商品限购设置表")
public class ProdPurchaseRestrictions {
    /**
     * 商品id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "prod_id", type = IdType.ASSIGN_ID)
    @ApiModelProperty("商品id")
    private Long prodId;

    /**
     * 每次购买最大件数
     */
    @ApiModelProperty("每次购买最大件数")
    @TableField(value = "each_purchases_maximum")
    private Integer eachPurchasesMaximum;

    /**
     * 累计最多购买件数
     */
    @ApiModelProperty("累计最多购买件数")
    @TableField(value = "umulative_purchases_num")
    private Integer umulativePurchasesNum;

    /**
     * 最少购买件数
     */
    @ApiModelProperty("最少购买件数")
    @TableField(value = "each_purchases_minimum")
    private Integer eachPurchasesMinimum;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create" ,fill = FieldFill.INSERT)
    private Date gmtCreate;

}
