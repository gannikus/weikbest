package com.weikbest.pro.saas.merchat.aftersale.module.vo;

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
 * 订单售后图片拆分历史表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleImgHisVO对象", description = "订单售后图片拆分历史表")
public class OrderAfterSaleImgHisVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后协商历史ID")
    private Long historyId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单售后ID")
    private Long id;

    @ApiModelProperty("售后图片链接")
    private String courierImgPath;


}