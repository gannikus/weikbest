package com.weikbest.pro.saas.merchat.aftersale.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "OrderAfterSaleImgHisDTO对象", description = "订单售后图片拆分历史表")
public class OrderAfterSaleImgHisDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "售后协商历史ID不为空!")
    @ApiModelProperty(value = "售后协商历史ID", required = true)
    private Long historyId;


    @NotBlank(message = "售后图片链接不为空!")
    @ApiModelProperty(value = "售后图片链接", required = true)
    private String courierImgPath;


}