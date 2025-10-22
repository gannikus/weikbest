package com.weikbest.pro.saas.sys.system.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "OrgQO对象", description = "系统部门表")
public class OrgQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("部门名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上级部门id")
    private Long parentId;

    @ApiModelProperty("部门排序")
    private Integer orgOrd;

    @ApiModelProperty("层级")
    private Integer orgLevel;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("状态 0-禁用 1-有效")
    private String dataStatus;


}