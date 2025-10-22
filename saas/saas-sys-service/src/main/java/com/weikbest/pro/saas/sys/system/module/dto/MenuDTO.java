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
 * 系统菜单表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "MenuDTO对象", description = "系统菜单表")
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "模块ID不为空!")
    @ApiModelProperty(value = "模块ID", required = true)
    private Long menuModuleId;

    @NotBlank(message = "菜单名称不为空!")
    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "上级菜单id不为空!")
    @ApiModelProperty(value = "上级菜单id", required = true)
    private Long parentId;

    @ApiModelProperty(value = "菜单提示")
    private String tips;

    @ApiModelProperty(value = "菜单url")
    private String url;

    @ApiModelProperty(value = "层级", required = true)
    private Integer menuLevel;

    @ApiModelProperty(value = "菜单排序", required = true)
    private Integer menuOrd;

    @NotBlank(message = "菜单类型不为空!")
    @ApiModelProperty(value = "菜单类型 0-平台菜单 1-商家端菜单 2-APP端菜单", required = true)
    private String menuType;

    @ApiModelProperty(value = "图标")
    private String iconClass;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}