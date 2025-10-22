package com.weikbest.pro.saas.merchat.prod.module.vo;

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
 * 商品装修落地页广告回传信息拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAdvBackAccountVO对象", description = "商品装修落地页广告回传信息拆分多行表")
public class PayBackProdAdvBackAccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品id ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联广告账户ID")
    private Long advAccountId;

    @ApiModelProperty("回传比率")
    private BigDecimal backRatio;

    @ApiModelProperty("支付成功回传 0-不回传 1-回传")
    private String successPayBack;

    @ApiModelProperty("广告回传类型 1-全部回传 2-按投放账号回传")
    private String advBackType;


}