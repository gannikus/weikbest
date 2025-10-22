package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 物流快递公司表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "LogisticsCompanyDTO对象", description = "物流快递公司表")
public class LogisticsCompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "物流快递公司编码不为空!")
    @ApiModelProperty(value = "物流快递公司编码", required = true)
    private String number;

    @NotBlank(message = "物流快递公司名称不为空!")
    @ApiModelProperty(value = "物流快递公司名称", required = true)
    private String name;


}