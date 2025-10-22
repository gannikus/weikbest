package com.weikbest.pro.saas.merchat.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdShowDetailVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 店铺优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopRefluxCouponVO对象", description = "店铺回流优惠券对象")
public class ShopPromptlyCouponVO extends CouponVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;

    @ApiModelProperty(value = "适用商品集合")
    private List<ProdShowDetailVO> prodList;

    @ApiModelProperty(value = "指定客户手机号集合")
    private List<String> customerPhoneList;

}