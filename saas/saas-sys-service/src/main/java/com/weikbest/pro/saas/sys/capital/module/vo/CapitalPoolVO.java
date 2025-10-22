package com.weikbest.pro.saas.sys.capital.module.vo;

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
 * 平台资金池表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CapitalPoolVO对象", description = "平台资金池表")
public class CapitalPoolVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("余额")
    private BigDecimal banance;

    @ApiModelProperty("累计收入")
    private BigDecimal total;


}