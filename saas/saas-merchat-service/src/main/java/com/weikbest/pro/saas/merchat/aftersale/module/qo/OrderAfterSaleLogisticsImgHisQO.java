package com.weikbest.pro.saas.merchat.aftersale.module.qo;

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
 * 订单售后物流图片拆分历史表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleLogisticsImgHisQO对象", description = "订单售后物流图片拆分历史表")
public class OrderAfterSaleLogisticsImgHisQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后协商历史ID")
    private Long historyId;

    @ApiModelProperty("快递物流图片类型 1-商家发货 2-客户寄回 3-商家再次发货")
    private String courierImgType;

    @ApiModelProperty("快递图片链接")
    private String courierImgPath;


}