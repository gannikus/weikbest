package com.weikbest.pro.saas.sys.system.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统部门表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrgDTO对象", description = "系统部门表")
public class OrgDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "部门名称不为空!")
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "上级部门id不为空!")
    @ApiModelProperty(value = "上级部门id", required = true)
    private Long parentId;

    @ApiModelProperty(value = "部门排序", required = true)
    private Integer orgOrd;

    @ApiModelProperty(value = "层级", required = true)
    private Integer orgLevel;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("状态 0-禁用 1-有效")
    private String dataStatus;


}