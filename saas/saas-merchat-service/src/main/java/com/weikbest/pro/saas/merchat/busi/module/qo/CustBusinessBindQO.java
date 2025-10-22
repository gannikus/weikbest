package com.weikbest.pro.saas.merchat.busi.module.qo;

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
 * 分账商户绑定表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustBusinessBindQO对象", description = "分账商户绑定表")
public class CustBusinessBindQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty("订单号")
    private String number;

    @ApiModelProperty("关联小程序appid")
    private String appId;

    @ApiModelProperty("绑定时间 yyyy-MM-dd HH:mm:ss")
    private Date bindTime;

    @ApiModelProperty("有效期")
    private String validityDay;

    @ApiModelProperty("绑定状态 1-绑定 2-解除绑定")
    private String bindStatus;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}