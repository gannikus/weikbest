package com.weikbest.pro.saas.sys.param.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统编码规则表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CodeRuleQO对象", description = "系统编码规则表")
public class CodeRuleQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("编码规则编码")
    private String number;

    @ApiModelProperty("编码规则名称")
    private String name;

    @ApiModelProperty("编码规则类型 1-流水号 2-日期时间+流水号")
    private String ruleType;

    @ApiModelProperty("固定前缀")
    private String prefix;

    @ApiModelProperty("连接符号")
    private String connector;

    @ApiModelProperty("初始编码位数")
    private Integer initDigit;

    @ApiModelProperty("实际编码位数")
    private Integer realDigit;

    @ApiModelProperty("当前编码")
    private String currNum;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}