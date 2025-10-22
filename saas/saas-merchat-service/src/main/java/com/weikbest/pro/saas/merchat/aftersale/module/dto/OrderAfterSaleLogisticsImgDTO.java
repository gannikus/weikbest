package com.weikbest.pro.saas.merchat.aftersale.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 订单售后物流图片拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleLogisticsImgDTO对象", description = "订单售后物流图片拆分表")
public class OrderAfterSaleLogisticsImgDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "快递物流图片类型不为空!")
    @ApiModelProperty(value = "快递物流图片类型 1-商家发货 2-客户寄回 3-商家再次发货", required = true)
    private String courierImgType;

    @NotBlank(message = "快递图片链接不为空!")
    @ApiModelProperty(value = "快递图片链接", required = true)
    private String courierImgPath;


}