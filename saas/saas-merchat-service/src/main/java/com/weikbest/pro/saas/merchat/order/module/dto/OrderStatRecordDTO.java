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
import java.util.Date;

/**
 * <p>
 * 订单状态变更记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderStatRecordDTO对象", description = "订单状态变更记录表 ")
public class OrderStatRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单ID不为空!")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "当前状态（订单状态）不为空!")
    @ApiModelProperty(value = "当前状态（订单状态）", required = true)
    private String currentState;

    @NotBlank(message = "变更状态（订单状态）不为空!")
    @ApiModelProperty(value = "变更状态（订单状态）", required = true)
    private String changeStatus;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "变更人ID不为空!")
    @ApiModelProperty(value = "变更人ID", required = true)
    private Long changerUserId;

    @NotBlank(message = "变更人不为空!")
    @ApiModelProperty(value = "变更人", required = true)
    private String changerUser;

    @NotBlank(message = "变更时间 yyyy-MM-dd HH:mm:ss不为空!")
    @ApiModelProperty(value = "变更时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date changeTime;

    @ApiModelProperty("备注")
    private String description;


}