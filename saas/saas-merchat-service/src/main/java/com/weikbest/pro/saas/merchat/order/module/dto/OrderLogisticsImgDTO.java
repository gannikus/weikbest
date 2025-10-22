package com.weikbest.pro.saas.merchat.order.module.dto;

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
@ApiModel(value = "OrderLogisticsImgDTO对象", description = "订单物流图片表")
public class OrderLogisticsImgDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "快递图片链接不为空!")
    @ApiModelProperty(value = "快递图片链接", required = true)
    private String courierImgPath;


}