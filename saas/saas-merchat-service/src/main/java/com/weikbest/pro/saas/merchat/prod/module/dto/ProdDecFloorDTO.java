package com.weikbest.pro.saas.merchat.prod.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "ProdDecFloorDTO对象", description = "商品装修落地页拆分表")
public class ProdDecFloorDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "页面名称不为空!")
    @ApiModelProperty(value = "页面名称", required = true)
    private String pageTitle;

    @NotBlank(message = "立即购买按钮文字不为空!")
    @ApiModelProperty(value = "立即购买按钮文字", required = true)
    private String buyBtnTitle;

    @NotBlank(message = "悬浮按钮文字不为空!")
    @ApiModelProperty(value = "悬浮按钮文字", required = true)
    private String floatBtnTitle;

    @NotBlank(message = "优惠倒计时不为空!")
    @ApiModelProperty(value = "优惠倒计时 0-不开启 1-开启", required = true)
    private String countdownOffers;

    @ApiModelProperty(value = "优惠倒计时文字")
    private String countdownTitle;

    @ApiModelProperty(value = "优惠倒计时分钟数")
    private Integer countdownMinute;

    @NotBlank(message = "支付成功回传不为空!")
    @ApiModelProperty(value = "支付成功回传 0-不回传 1-回传", required = true)
    private String successPayBack;

    @ApiModelProperty(value = "广告回传类型 1-全部回传 2-按投放账号回传")
    private String advBackType;

    @ApiModelProperty("广告回传账号信息集合")
    private List<ProdAdvBackAccountDTO> prodAdvBackAccountDTOList = new ArrayList<>();


}