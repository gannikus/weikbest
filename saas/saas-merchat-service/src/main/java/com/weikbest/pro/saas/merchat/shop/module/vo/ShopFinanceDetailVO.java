package com.weikbest.pro.saas.merchat.shop.module.vo;

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
@ApiModel(value = "ShopFinanceDetailVO对象", description = "店铺资金明细表")
public class ShopFinanceDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "订单号")
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("资金账户ID")
    private Long financeAccountId;

    @ApiModelProperty("账务类型 1-订单收入 10-分账押金退回 20-分账押金扣款 30-售后退款 40-平台售后分账回退 50-技术服务费-自然流量")
    private String financeType;

    @ApiModelProperty("资金流向 1-收入 2-支出")
    private String capitalFlowType;

    @ApiModelProperty("入账时间")
    private Date enterTime;

    @ApiModelProperty("微信业务单号")
    private String wxOrderId;

    @ApiModelProperty("收支金额")
    private BigDecimal amountDetail;

    @ApiModelProperty("备注")
    private String description;


}