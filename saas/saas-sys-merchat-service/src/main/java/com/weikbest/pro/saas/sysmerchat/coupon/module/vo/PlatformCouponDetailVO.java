package com.weikbest.pro.saas.sysmerchat.coupon.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponVO;
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
 * 平台优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponDetailVO对象", description = "平台优惠券详情对象")
public class PlatformCouponDetailVO extends CouponVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;

    @ApiModelProperty(value = "适用商品集合")
    private List<ProdShowDetailVO> prodList;

}