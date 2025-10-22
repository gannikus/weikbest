package com.weikbest.pro.saas.merchat.order.module.qo;

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
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderLogisticsQO对象", description = "订单物流记录表")
public class OrderLogisticsQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("物流公司 字典表CODE")
    private String logisticsCompany;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("发货时间")
    private Date logisticsTime;

    @ApiModelProperty("第三方接口返回的物流信息")
    private String content;

    @ApiModelProperty("是否已修改 0-否 1-是")
    private String isUpdate;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("发货人")
    private String consigner;

    @ApiModelProperty("发货人联系方式")
    private String contact;

    @ApiModelProperty("发货地址 省、直辖市")
    private String addrProvince;

    @ApiModelProperty("发货地址 市")
    private String addrCity;

    @ApiModelProperty("发货地址 区、县")
    private String addrDistrict;

    @ApiModelProperty("发货详细地址 ")
    private String addr;
}