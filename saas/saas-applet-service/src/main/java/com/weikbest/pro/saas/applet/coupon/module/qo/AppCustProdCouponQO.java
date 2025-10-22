package com.weikbest.pro.saas.applet.coupon.module.qo;

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
 * <p>
 * 店铺优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppShopCouponQO对象", description = "店铺优惠券表")
public class AppCustProdCouponQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("核销小程序ID")
    private String appId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal comboPrice;

    @ApiModelProperty("1-回流，2-全部")
    private String couponType;

    @ApiModelProperty("0-不分页，1-分页")
    private String isPage;


}
