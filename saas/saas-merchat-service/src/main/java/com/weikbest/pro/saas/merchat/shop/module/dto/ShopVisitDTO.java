package com.weikbest.pro.saas.merchat.shop.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "ShopVisitDTO对象", description = "店铺访问表")
public class ShopVisitDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID 不为空!")
    @ApiModelProperty(value = "关联商品ID ", required = true)
    private Long prodId;

    @NotBlank(message = "关联小程序ID不为空!")
    @ApiModelProperty(value = "关联小程序ID", required = true)
    private String appId;

    @NotBlank(message = "访问店铺页面URL不为空!")
    @ApiModelProperty(value = "访问店铺页面URL", required = true)
    private String visitShopPage;

    @NotBlank(message = "访问商品页面URL不为空!")
    @ApiModelProperty(value = "访问商品页面URL", required = true)
    private String visitProdPage;

    @NotBlank(message = "访问类型 1-访问店铺 2-访问商品不为空!")
    @ApiModelProperty(value = "访问类型 1-访问店铺 2-访问商品", required = true)
    private String visitType;

    @ApiModelProperty(value = "访问时间", required = true)
    private Date visitTime;


}