package com.weikbest.pro.saas.merchat.prod.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "SlideFloorQO对象", description = "商品左滑和落地页信息")
public class SlideFloorQO implements Serializable {

    private static final long serialVersionUID = -7612692863134552239L;

    @ApiModelProperty("商品Id")
    private Long id;

    @ApiModelProperty("广告链接名称")
    private String adLinksName;

    @ApiModelProperty("支付成功回传 0-不回传 1-回传")
    private String successPayBack;

}
