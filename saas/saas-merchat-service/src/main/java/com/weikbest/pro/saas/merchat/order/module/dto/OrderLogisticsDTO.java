package com.weikbest.pro.saas.merchat.order.module.dto;

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
import java.util.List;

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
@ApiModel(value = "OrderLogisticsDTO对象", description = "订单物流记录表")
public class OrderLogisticsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单ID不为空!")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "物流公司 字典表CODE不为空!")
    @ApiModelProperty(value = "物流公司 字典表CODE", required = true)
    private String logisticsCompany;

    @NotBlank(message = "快递单号不为空!")
    @ApiModelProperty(value = "快递单号", required = true)
    private String courierNumber;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("是否批量发货 0-否 1-是")
    private String isBatchDeliver;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("批量发货记录ID")
    private Long batchDeliverId;

    @ApiModelProperty("订单物流图片列表")
    private List<OrderLogisticsImgDTO> orderLogisticsImgDTOList;

}