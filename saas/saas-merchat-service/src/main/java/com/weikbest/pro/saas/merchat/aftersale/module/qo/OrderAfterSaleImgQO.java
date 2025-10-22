package com.weikbest.pro.saas.merchat.aftersale.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "OrderAfterSaleImgQO对象", description = "订单售后图片拆分表")
public class OrderAfterSaleImgQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("售后图片链接")
    private String courierImgPath;


}