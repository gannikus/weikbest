package com.weikbest.pro.saas.sys.capital.entity;

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
 * 平台资金出入账记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_capital_record")
@ApiModel(value = "CapitalRecord对象", description = "平台资金出入账记录表 ")
public class CapitalRecord implements Serializable {

    public static final String ID = "id";
    public static final String BILL_TYPE = "bill_type";
    public static final String SHOP_ID = "shop_id";
    public static final String NUMBER = "number";
    public static final String BILL_AMOUNT = "bill_amount";
    public static final String ACCOUNT_BANANCE = "account_banance";
    public static final String DESCRIPTION = "description";
    public static final String BILL_TIME = "bill_time";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField(value = "shop_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String shopId;
    @ApiModelProperty("订单号")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("账单来源")
    @TableField(value = "bill_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String billType;
    @ApiModelProperty("账单金额")
    @TableField(value = "bill_amount", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String billAmount;
    @ApiModelProperty("账户余额")
    @TableField("account_banance")
    private BigDecimal accountBanance;
    @ApiModelProperty("记账事件")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("记账时间 yyyy-MM-dd HH:mm:ss")
    @TableField("bill_time")
    private Date billTime;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField("modifier")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
