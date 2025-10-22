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
@ApiModel(value = "OrderStatRecordVO对象", description = "订单状态变更记录表 ")
public class OrderStatRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("当前状态（订单状态）")
    private String currentState;

    @ApiModelProperty("变更状态（订单状态）")
    private String changeStatus;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("变更人ID")
    private Long changerUserId;

    @ApiModelProperty("变更人")
    private String changerUser;

    @ApiModelProperty("变更时间 yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

    @ApiModelProperty("备注")
    private String description;


}