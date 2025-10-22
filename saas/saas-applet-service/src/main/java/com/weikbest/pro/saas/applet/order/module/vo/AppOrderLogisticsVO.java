package com.weikbest.pro.saas.applet.order.module.vo;

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
 * 订单物流记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderLogisticsVO对象", description = "订单物流记录表")
public class AppOrderLogisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("物流公司 字典表CODE")
    private String logisticsCompany;

    @ApiModelProperty("物流公司 字典表名称")
    private String logisticsCompanyName;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("发货时间")
    private Date logisticsTime;

    @ApiModelProperty("快递图片(多个用,隔开)")
    private String courierImgPath;

    @ApiModelProperty("第三方接口返回的物流信息")
    private String content;

    @ApiModelProperty("备注")
    private String description;


}