package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("t_sys_settle")
@ApiModel(value = "Settle对象", description = "系统结算规则表")
public class Settle implements Serializable {

    public static final String ID = "id";
    public static final String BIND_CUSTOMER_MAX_DAY = "bind_customer_max_day";
    public static final String BIND_CUSTOMER_CONTINUE_DAY = "bind_customer_continue_day";
    public static final String SPLIT_DEPOSIT_RATIO = "split_deposit_ratio";
    public static final String PLATFORM_SPLITTING_RATIO = "platform_splitting_ratio";
    public static final String TECHNICAL_LEDGER_RATIO = "technical_ledger_ratio";
    public static final String COMMISSION_RATIO = "commission_ratio";
    public static final String ORDER_FUND_RELEASE_HOUR = "order_fund_release_hour";
    public static final String REBATE_OF_DEPOSIT_DAY = "rebate_of_deposit_day";
    public static final String IS_OPEN_CMS = "is_open_cms";
    public static final String IS_OPEN_CMS_DETAIL = "is_open_cms_detail";
    public static final String UPDATE_CMS_TYPE = "update_cms_type";
    public static final String IS_OPEN_CMS_WITHDRAWAL_REVIEW = "is_open_cms_withdrawal_review";
    public static final String UPDATE_CMS_TIME = "update_cms_time";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("绑定客户关系最长有效天数")
    @TableField("bind_customer_max_day")
    private Integer bindCustomerMaxDay;
    @ApiModelProperty("绑定客户关系持续天数")
    @TableField("bind_customer_continue_day")
    private Integer bindCustomerContinueDay;
    @ApiModelProperty("分账押金比率")
    @TableField("split_deposit_ratio")
    private BigDecimal splitDepositRatio;
    @ApiModelProperty("自然流量平台分账比率")
    @TableField("platform_splitting_ratio")
    private BigDecimal platformSplittingRatio;
    @ApiModelProperty("平台及技术分账比率")
    @TableField("technical_ledger_ratio")
    private BigDecimal technicalLedgerRatio;
    @ApiModelProperty("佣金比率")
    @TableField("commission_ratio")
    private BigDecimal commissionRatio;
    @ApiModelProperty("订单资金解冻小时数")
    @TableField("order_fund_release_hour")
    private Integer orderFundReleaseHour;
    @ApiModelProperty("分账押金回退天数")
    @TableField("rebate_of_deposit_day")
    private Integer rebateOfDepositDay;
    @ApiModelProperty("开启佣金收益 0-否 1-是")
    @TableField(value = "is_open_cms", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCms;
    @ApiModelProperty("开启显示佣金明细 0-否 1-是")
    @TableField(value = "is_open_cms_detail", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCmsDetail;
    @ApiModelProperty("佣金收益总额更新方式 0-手动更新 1-系统自动更新")
    @TableField(value = "update_cms_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String updateCmsType;
    @ApiModelProperty("开启佣金提现审核 0-否 1-是")
    @TableField(value = "is_open_cms_withdrawal_review", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCmsWithdrawalReview;
    @ApiModelProperty("佣金收益总额更新时间 HH:mm:ss")
    @TableField("update_cms_time")
    private String updateCmsTime;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
