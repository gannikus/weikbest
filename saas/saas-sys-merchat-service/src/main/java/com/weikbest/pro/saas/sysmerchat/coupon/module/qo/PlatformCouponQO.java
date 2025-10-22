package com.weikbest.pro.saas.sysmerchat.coupon.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 平台优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PlatformCouponQO对象", description = "平台优惠券表")
public class PlatformCouponQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("优惠券id")
    private Long id;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消")
    private String couponStatus;

    @ApiModelProperty("创建开始时间")
    private Date startDate;

    @ApiModelProperty("创建结束时间")
    private Date endDate;

    @ApiModelProperty("创建时间排序 asc-从小到大 desc-从大到小，默认asc")
    private String orderByGmtCreate;

}