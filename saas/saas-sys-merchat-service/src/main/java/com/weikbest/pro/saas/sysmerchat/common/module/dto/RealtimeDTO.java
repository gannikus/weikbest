package com.weikbest.pro.saas.sysmerchat.common.module.dto;

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
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RealtimeDTO对象", description = "首页实时数据对象")
public class RealtimeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "关联小程序ID")
    private String appId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店商品ID")
    private Long prodId;

    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private String orderSource;



    /** 支付信息表 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("支付时间 yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("付款金额")
    private BigDecimal payAmount;

    @ApiModelProperty("付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败")
    private String payStatus;

    @ApiModelProperty("退款状态 0-未退款 1-已退款 2-退款中")
    private String refundStatus;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;


    /** 商品信息 */
    @ApiModelProperty("数量")
    private Integer buyNumber;
}
