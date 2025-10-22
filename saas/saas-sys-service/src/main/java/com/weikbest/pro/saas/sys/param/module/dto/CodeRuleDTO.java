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
@ApiModel(value = "CodeRuleDTO对象", description = "系统编码规则表")
public class CodeRuleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "编码规则编码不为空!")
    @ApiModelProperty(value = "编码规则编码", required = true)
    private String number;

    @NotBlank(message = "编码规则名称不为空!")
    @ApiModelProperty(value = "编码规则名称", required = true)
    private String name;

    @ApiModelProperty("编码规则类型 1-流水号 2-日期时间+流水号")
    private String ruleType;

    @NotBlank(message = "固定前缀不为空!")
    @ApiModelProperty(value = "固定前缀", required = true)
    private String prefix;

    @NotBlank(message = "连接符号不为空!")
    @ApiModelProperty(value = "连接符号", required = true)
    private String connector;

    @ApiModelProperty(value = "初始编码位数", required = true)
    private Integer initDigit;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}