package com.weikbest.pro.saas.sys.param.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "LogisticsCompanyVO对象", description = "物流快递公司表")
public class LogisticsCompanyVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("物流快递公司编码")
    private String number;

    @ApiModelProperty("物流快递公司名称")
    private String name;


}