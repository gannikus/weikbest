package com.weikbest.pro.saas.merchat.shop.module.qo;

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
@ApiModel(value = "ShopVisitQO对象", description = "店铺访问表")
public class ShopVisitQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    private Long prodId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("访问店铺页面URL")
    private String visitShopPage;

    @ApiModelProperty("访问商品页面URL")
    private String visitProdPage;

    @ApiModelProperty("访问类型 1-访问店铺 2-访问商品")
    private String visitType;

    @ApiModelProperty("访问时间")
    private Date visitTime;


}