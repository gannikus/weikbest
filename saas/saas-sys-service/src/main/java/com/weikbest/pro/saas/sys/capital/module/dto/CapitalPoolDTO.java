package com.weikbest.pro.saas.sys.capital.module.dto;

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
@ApiModel(value = "CapitalPoolDTO对象", description = "平台资金池表")
public class CapitalPoolDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "余额", required = true)
    private BigDecimal banance;

    @ApiModelProperty(value = "累计收入", required = true)
    private BigDecimal total;


}