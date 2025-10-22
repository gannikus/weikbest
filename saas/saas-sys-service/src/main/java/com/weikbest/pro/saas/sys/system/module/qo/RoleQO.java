package com.weikbest.pro.saas.sys.system.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RoleQO对象", description = "系统角色表")
public class RoleQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("编码")
    private String number;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("角色类型 0-平台角色 1-商家端角色 2-APP端角色")
    private String roleType;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}