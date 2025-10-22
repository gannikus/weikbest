package com.weikbest.pro.saas.sys.param.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 系统结算规则表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SettleVO对象", description = "系统结算规则表")
public class SettleVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("绑定客户关系最长有效天数")
    private Integer bindCustomerMaxDay;

    @ApiModelProperty("绑定客户关系持续天数")
    private Integer bindCustomerContinueDay;

    @ApiModelProperty("分账押金比率")
    private BigDecimal splitDepositRatio;

    @ApiModelProperty("自然流量平台分账比率")
    private BigDecimal platformSplittingRatio;

    @ApiModelProperty("平台及技术分账比率")
    private BigDecimal technicalLedgerRatio;

    @ApiModelProperty("佣金比率")
    private BigDecimal commissionRatio;

    @ApiModelProperty("订单资金解冻小时数")
    private Integer orderFundReleaseHour;

    @ApiModelProperty("分账押金回退天数")
    private Integer rebateOfDepositDay;

    @ApiModelProperty("开启佣金收益 0-否 1-是")
    private String isOpenCms;

    @ApiModelProperty("开启显示佣金明细 0-否 1-是")
    private String isOpenCmsDetail;

    @ApiModelProperty("佣金收益总额更新方式 0-手动更新 1-系统自动更新")
    private String updateCmsType;

    @ApiModelProperty("开启佣金提现审核 0-否 1-是")
    private String isOpenCmsWithdrawalReview;

    @ApiModelProperty("佣金收益总额更新时间 HH:mm:ss")
    private String updateCmsTime;


}