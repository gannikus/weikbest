package com.weikbest.pro.saas.merchat.prod.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 广告商品优惠劵拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdCouponDTO对象", description = "广告商品优惠劵拆分表")
public class ProdCouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "店铺优惠券类型 不为空!")
    @ApiModelProperty(value = "店铺优惠券类型 1-商品立减劵 2-回流优惠券", required = true)
    private String shopCouponType;

    @ApiModelProperty(value = "是否设置优惠券 0-否 1-是")
    private String isOpenCoupon;

    @NotBlank(message = "主动核销 不为空!")
    @ApiModelProperty(value = "主动核销 0-不主动 1-主动", required = true)
    private String chargeOff;

    @ApiModelProperty(value = "领劵弹窗图片")
    private String couponOpenImg;

    @ApiModelProperty(value = "回传比例")
    private BigDecimal callbackProportion;

    @ApiModelProperty("商品绑定优惠券数据集合")
    private List<ProdCouponRelateDTO> prodCouponRelateDTOList = new ArrayList<>();

}
