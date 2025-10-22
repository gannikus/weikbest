package com.weikbest.pro.saas.sys.param.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统交易规则表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DealVO对象", description = "系统交易规则表")
public class DealVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("未付款自动关闭分钟数")
    private Integer closeOrderMinute;

    @ApiModelProperty("自动收货天数")
    private Integer autoOrderReceive;

    @ApiModelProperty("收货后订单自动完成时间")
    private Integer autoOrderComplete;

    @ApiModelProperty("极速退款条件：订单金额元以内")
    private Integer fastRefundCondition1;

    @ApiModelProperty("极速退款条件：下单小时内")
    private Integer fastRefundCondition2;

    @ApiModelProperty("极速退款条件：申请退款后未操作天数")
    private Integer fastRefundCondition3;

    @ApiModelProperty("开启极速退款条件：下单小时内 0-否 1-是")
    private String isOpenCondition2;

    @ApiModelProperty("开启极速退款条件：申请退款后未操作天数 0-否 1-是")
    private String isOpenCondition3;

    @ApiModelProperty("买家未处理，系统自动关闭售后时间天")
    private Integer refundCustomerTimeout;

    @ApiModelProperty("商家未处理，系统自动通过售后时间天")
    private Integer refundBusinessTimeout;

    @ApiModelProperty("维权被拒绝后，系统自动关闭维权时间天")
    private Integer refundRepealTimeout;

    @ApiModelProperty("开启订单评价 0-否 1-是")
    private String isOpenOrderComment;

    @ApiModelProperty("显示订单评价 0-否 1-是")
    private String isShowOrderComment;

    @ApiModelProperty("审核订单评价 0-否 1-是")
    private String isReviewOrderComment;


}