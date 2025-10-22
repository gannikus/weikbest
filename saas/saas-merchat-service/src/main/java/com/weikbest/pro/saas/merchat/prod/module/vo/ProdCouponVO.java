package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProdCouponVO对象", description = "广告商品优惠劵拆分表")
public class ProdCouponVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("店铺优惠券类型 1-商品立减劵 2-回流优惠券")
    private String shopCouponType;

    @ApiModelProperty(value = "是否设置优惠券 0-否 1-是")
    private String isOpenCoupon;

    @ApiModelProperty("主动核销 0-不主动 1-主动")
    private String chargeOff;

    @ApiModelProperty("领劵弹窗图片")
    private String couponOpenImg;

    @ApiModelProperty("营销回传比例")
    private BigDecimal callbackProportion;

    @ApiModelProperty("商品回流优惠券数据字典")
    private List<DictEntry> dictEntryList = new ArrayList<>();


}
