package com.weikbest.pro.saas.merchat.prod.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品装修落地页拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdDecFloorVO对象", description = "商品装修落地页拆分表")
public class ProdDecFloorVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页面名称")
    private String pageTitle;

    @ApiModelProperty("立即购买按钮文字")
    private String buyBtnTitle;

    @ApiModelProperty("悬浮按钮文字")
    private String floatBtnTitle;

    @ApiModelProperty("优惠倒计时 0-不开启 1-开启")
    private String countdownOffers;

    @ApiModelProperty("优惠倒计时文字")
    private String countdownTitle;

    @ApiModelProperty("优惠倒计时分钟数")
    private Integer countdownMinute;

    @ApiModelProperty("支付成功回传 0-不回传 1-回传")
    private String successPayBack;

    @ApiModelProperty("广告回传类型 1-全部回传 2-按投放账号回传")
    private String advBackType;

    @ApiModelProperty("广告回传账号信息集合")
    private List<ProdAdvBackAccountVO> prodAdvBackAccountVOList = new ArrayList<>();


}