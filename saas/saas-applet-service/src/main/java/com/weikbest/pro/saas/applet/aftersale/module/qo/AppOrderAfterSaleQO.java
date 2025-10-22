package com.weikbest.pro.saas.applet.aftersale.module.qo;

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
 * 订单售后表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderAfterSaleQO对象", description = "订单售后表")
public class AppOrderAfterSaleQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    private Long customerId;

    @ApiModelProperty("处理中，1-是")
    private String inprocess;


}