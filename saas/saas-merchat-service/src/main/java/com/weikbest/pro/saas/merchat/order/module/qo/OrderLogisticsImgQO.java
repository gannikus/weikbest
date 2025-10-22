package com.weikbest.pro.saas.merchat.order.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单物流图片表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderLogisticsImgQO对象", description = "订单物流图片表")
public class OrderLogisticsImgQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("快递图片链接")
    private String courierImgPath;


}