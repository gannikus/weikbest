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
 * 订单售后图片拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleImgDTO对象", description = "订单售后图片拆分表")
public class OrderAfterSaleImgDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "售后图片链接不为空!")
    @ApiModelProperty(value = "售后图片链接", required = true)
    private String courierImgPath;


}