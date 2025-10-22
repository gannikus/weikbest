package com.weikbest.pro.saas.applet.shop.module.dto;

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
@ApiModel(value = "ShopVisitVO对象", description = "店铺访问表")
public class AppShopVisitDTO implements Serializable {

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

}