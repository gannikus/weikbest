package com.weikbest.pro.saas.merchat.shop.entity;

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
 * 店铺资金明细表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop_finance_detail")
@ApiModel(value = "ShopFinanceDetail对象", description = "店铺资金明细表")
public class ShopFinanceDetail implements Serializable {

    public static final String ID = "id";
    public static final String SHOP_ID = "shop_id";
    public static final String NUMBER = "number";
    public static final String FINANCE_ACCOUNT_ID = "finance_account_id";
    public static final String FINANCE_TYPE = "finance_type";
    public static final String CAPITAL_FLOW_TYPE = "capital_flow_type";
    public static final String ENTER_TIME = "enter_time";
    public static final String WX_ORDER_ID = "wx_order_id";
    public static final String AMOUNT_DETAIL = "amount_detail";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @ApiModelProperty("订单号")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("资金账户ID")
    @TableField("finance_account_id")
    private Long financeAccountId;
    @ApiModelProperty("账务类型 1-订单收入 10-分账押金退回 20-分账押金扣款 30-售后退款 40-平台售后分账回退 50-技术服务费-自然流量")
    @TableField(value = "finance_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String financeType;
    @ApiModelProperty("资金流向 1-收入 2-支出")
    @TableField(value = "capital_flow_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String capitalFlowType;
    @ApiModelProperty("入账时间")
    @TableField("enter_time")
    private Date enterTime;
    @ApiModelProperty("微信业务单号")
    @TableField(value = "wx_order_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxOrderId;
    @ApiModelProperty("收支金额")
    @TableField("amount_detail")
    private BigDecimal amountDetail;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
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
