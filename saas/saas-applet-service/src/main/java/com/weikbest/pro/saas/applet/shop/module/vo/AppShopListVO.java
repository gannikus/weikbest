package com.weikbest.pro.saas.applet.shop.module.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppShopListVO对象", description = "商户店铺表")
public class AppShopListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @ApiModelProperty("店铺编码")
    private String number;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("是否品牌店铺 0-否 1-是")
    private String isBrand;

    @ApiModelProperty("营业执照")
    private String businessListence;

    @ApiModelProperty("商品数")
    private Long coundProd;

    @ApiModelProperty("店铺商品列表")
    private List<AppProdDTO> appProdDTOList;

}