package com.weikbest.pro.saas.merchat.order.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@ApiModel(value = "OrderLogisticsImgVO对象", description = "订单物流图片表")
public class OrderLogisticsImgVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单物流ID")
    private Long id;

    @ApiModelProperty("快递图片链接")
    private String courierImgPath;


}