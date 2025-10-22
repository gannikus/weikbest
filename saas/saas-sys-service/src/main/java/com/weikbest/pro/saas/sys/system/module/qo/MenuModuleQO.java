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
 * 系统模块表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "MenuModuleQO对象", description = "系统模块表")
public class MenuModuleQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("模块编码")
    private String number;

    @ApiModelProperty("模块名称")
    private String name;

    @ApiModelProperty("模块排序")
    private Integer moduleOrd;

    @ApiModelProperty("模块类型 0-平台模块 1-商家端模块 2-APP端模块")
    private String moduleType;

    @ApiModelProperty("图标")
    private String iconClass;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}