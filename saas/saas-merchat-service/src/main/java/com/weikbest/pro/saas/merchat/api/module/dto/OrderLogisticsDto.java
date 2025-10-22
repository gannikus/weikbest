package com.weikbest.pro.saas.merchat.api.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsImgDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 订单物流记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderLogisticsDto对象", description = "订单物流")
public class OrderLogisticsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty(value = "订单编号",required = true)
    private String orderNumber;

    @NotBlank(message = "物流公司 字典表CODE不为空!")
    @ApiModelProperty(value = "物流公司 字典表CODE", required = true)
    private String logisticsCompany;

    @NotBlank(message = "快递单号不为空!")
    @ApiModelProperty(value = "快递单号", required = true)
    private String courierNumber;

    @ApiModelProperty("备注")
    private String description;

}
