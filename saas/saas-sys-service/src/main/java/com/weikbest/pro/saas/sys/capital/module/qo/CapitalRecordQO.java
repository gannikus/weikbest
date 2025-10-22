package com.weikbest.pro.saas.sys.capital.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "CapitalRecordQO对象", description = "平台资金出入账记录表 ")
public class CapitalRecordQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联店铺ID")
    private String shopId;

    @ApiModelProperty(value = "订单号")
    private String number;

    @ApiModelProperty("账单来源")
    private String billType;

    @ApiModelProperty("账单金额")
    private String billAmount;

    @ApiModelProperty("账户余额")
    private BigDecimal accountBanance;

    @ApiModelProperty("记账事件")
    private String description;

    @ApiModelProperty("记账时间 yyyy-MM-dd HH:mm:ss")
    private Date billTime;


}