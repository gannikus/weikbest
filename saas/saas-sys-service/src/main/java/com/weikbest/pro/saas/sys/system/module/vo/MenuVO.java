package com.weikbest.pro.saas.sys.system.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@ApiModel(value = "MenuVO对象", description = "系统菜单表")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("菜单id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("模块ID")
    private Long menuModuleId;

    @ApiModelProperty("菜单名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上级菜单id")
    private Long parentId;

    @ApiModelProperty("菜单提示")
    private String tips;

    @ApiModelProperty("菜单url")
    private String url;

    @ApiModelProperty("层级")
    private Integer menuLevel;

    @ApiModelProperty("菜单排序")
    private Integer menuOrd;

    @ApiModelProperty("菜单类型 0-平台菜单 1-商家端菜单 2-APP端菜单")
    private String menuType;

    @ApiModelProperty("图标")
    private String iconClass;

    @ApiModelProperty("菜单关联权限项")
    private List<MenuPermVO> menuPermVOList = new ArrayList<>();
}