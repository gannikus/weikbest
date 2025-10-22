package com.weikbest.pro.saas.sys.capital.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "CapitalRecordDTO对象", description = "平台资金出入账记录表 ")
public class CapitalRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private String shopId;

    @NotBlank(message = "订单号不为空!")
    @ApiModelProperty(value = "订单号", required = true)
    private String number;

    @NotBlank(message = "账单来源不为空!")
    @ApiModelProperty(value = "账单来源", required = true)
    private String billType;

    @NotBlank(message = "账单金额不为空!")
    @ApiModelProperty(value = "账单金额", required = true)
    private String billAmount;

    @ApiModelProperty(value = "账户余额", required = true)
    private BigDecimal accountBanance;

    @ApiModelProperty("记账事件")
    private String description;

    @ApiModelProperty(value = "记账时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date billTime;


}