package com.weikbest.pro.saas.sys.param.module.dto;

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
@ApiModel(value = "SettleDTO对象", description = "系统结算规则表")
public class SettleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "绑定客户关系最长有效天数")
    private Integer bindCustomerMaxDay;

    @ApiModelProperty(value = "绑定客户关系持续天数")
    private Integer bindCustomerContinueDay;

    @ApiModelProperty(value = "分账押金比率")
    private BigDecimal splitDepositRatio;

    @ApiModelProperty(value = "自然流量平台分账比率")
    private BigDecimal platformSplittingRatio;

    @ApiModelProperty(value = "平台及技术分账比率")
    private BigDecimal technicalLedgerRatio;

    @ApiModelProperty(value = "佣金比率")
    private BigDecimal commissionRatio;

    @ApiModelProperty(value = "订单资金解冻小时数")
    private Integer orderFundReleaseHour;

    @ApiModelProperty(value = "分账押金回退天数")
    private Integer rebateOfDepositDay;

    @ApiModelProperty(value = "开启佣金收益 0-否 1-是")
    private String isOpenCms;

    @ApiModelProperty(value = "开启显示佣金明细 0-否 1-是")
    private String isOpenCmsDetail;

    @ApiModelProperty(value = "佣金收益总额更新方式 0-手动更新 1-系统自动更新")
    private String updateCmsType;

    @ApiModelProperty(value = "开启佣金提现审核 0-否 1-是")
    private String isOpenCmsWithdrawalReview;

    @ApiModelProperty(value = "佣金收益总额更新时间 HH:mm:ss")
    private String updateCmsTime;


}