package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@Accessors(chain = true)
@ApiModel(value = "ProdPurchaseRestrictionsVo对象", description = "商品限购设置表")
public class ProdPurchaseRestrictionsVo {
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prodId;

    /**
     * 每次购买最大件数
     */
    @ApiModelProperty("每次购买最大件数")
    private Integer eachPurchasesMaximum;

    /**
     * 累计最多购买件数
     */
    @ApiModelProperty("累计最多购买件数")
    private Integer umulativePurchasesNum;

    /**
     * 最少购买件数
     */
    @ApiModelProperty("最少购买件数")
    private Integer eachPurchasesMinimum;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

}
