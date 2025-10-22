package com.weikbest.pro.saas.merchat.prod.module.qo;

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
import java.util.List;

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
@ApiModel(value = "ProdQO对象", description = "商品基本信息表")
public class ProdQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("类目ID")
    private Long categoryId;

    @ApiModelProperty("商品编码")
    private String number;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品内部备注")
    private String tips;

    @ApiModelProperty("商品状态 1-上架中 2-已下架")
    private String prodStatus;

    @ApiModelProperty("创建日期范围-开始 yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @ApiModelProperty("创建日期范围-结束 yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty("商品ID集合")
    private List<Long> ids;

    @ApiModelProperty(value = "商品类别 1- 自营商品 ，2-广告商品")
    private Integer goodsType;

    @ApiModelProperty(value = "商品随手购Id")
    private Long followShoppingId;
}
