package com.weikbest.pro.saas.applet.commodity.module.qo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdQO对象", description = "商品基本信息表")
public class AppProdQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品小程序类目ID ")
    private Long semCategotyId;

    @ApiModelProperty("查询所有营销分类下的商品标识值，有值时查询")
    private String types;

    @ApiModelProperty("排序标识，recommend：1 价格升序 2 价格降序 3 上新 4 销量")
    private String recommend;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前聚合页商品id")
    private Long prodId;

    @ApiModelProperty("小程序appId ")
    private String appId;

}
