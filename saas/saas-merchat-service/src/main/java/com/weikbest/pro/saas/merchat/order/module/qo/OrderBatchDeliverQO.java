package com.weikbest.pro.saas.merchat.order.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单批量发货记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderBatchDeliverQO对象", description = "订单批量发货记录表")
public class OrderBatchDeliverQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "关联商户ID 不为空！")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @NotNull(message = "关联店铺ID 不为空！")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;


    @ApiModelProperty("操作开始时间 yyyy-MM-dd HH:mm:ss")
    private Date recordStartTime;

    @ApiModelProperty("操作结束时间 yyyy-MM-dd HH:mm:ss")
    private Date recordEndTime;


    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("导入状态 0-失败 1-成功")
    private String importStatus;

    @ApiModelProperty("物流公司")
    private String logisticsCompanyName;

    @ApiModelProperty("快递单号")
    private String courierNumber;

}