package com.weikbest.pro.saas.merchat.prod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

/**
 * @author Mr.Wang
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_return")
@ApiModel(value = "ProdReturn", description = "商品返回页信息表")
public class ProdReturn implements Serializable {

    private static final long serialVersionUID = -1259284858192485351L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品Id")
    @TableField("prod_id")
    private Long prodId;

    @ApiModelProperty("返回页级别_1:首次挽回,2:二次挽回动漫,3:涨红包活动,4:惊喜福气卡,5:幸运大转盘,6:手气红包,7:摇摇乐,8:营销页弹框")
    @TableField("return_page")
    private Integer returnPage;

    @ApiModelProperty("优惠金额")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("回传是否开启_0:关闭,1:开启")
    @TableField("open_or_not")
    private Integer openOrNot;
}
