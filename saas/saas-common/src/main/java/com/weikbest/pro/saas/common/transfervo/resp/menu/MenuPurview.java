package com.weikbest.pro.saas.common.transfervo.resp.menu;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统权限信息表 s_menu
 * </p>
 *
 * @author wisdomelon
 * @since 2020-06-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MenuPurviewVO", description = "MenuPurviewVO")
public class MenuPurview implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单ID")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "上级菜单id")
    private Long parentId;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单提示标题")
    private String tips;

    @ApiModelProperty(value = "菜单链接")
    private String href;

    @ApiModelProperty(value = "层级 1:一级菜单 2：二级菜单 3：三级菜单...")
    private Integer menuLevel;

    @ApiModelProperty(value = "权限类别 1 菜单权限 2 路由权限 3 ajax操作权限")
    private Integer purviewType;

    @ApiModelProperty(value = "图标")
    private String iconClass;

    @ApiModelProperty(value = "是否有权限 Y 否  N  是")
    private String purview;

}
