package com.weikbest.pro.saas.merchat.busi.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusinessVO对象", description = "商户表")
public class BusinessVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商户编码")
    private String number;

    @ApiModelProperty("商户名称")
    private String name;

    @ApiModelProperty("商户类别")
    private String businessType;

    @ApiModelProperty("是否平台超级商户")
    private String isSuper;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

}