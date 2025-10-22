package com.weikbest.pro.saas.merchat.complaint.module.qo;

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
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintWxQO对象", description = "订单投诉微信查询对象")
public class OrderComplaintWxQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("开始日期 投诉发生的开始日期，格式为yyyy-MM-DD。注意，查询日期跨度不超过30天，当前查询为实时查询")
    private String beginDate;

    @ApiModelProperty("结束日期 投诉发生的结束日期，格式为yyyy-MM-DD。注意，查询日期跨度不超过30天，当前查询为实时查询")
    private String endDate;
}
