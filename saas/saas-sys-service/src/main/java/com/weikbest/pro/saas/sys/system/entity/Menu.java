package com.weikbest.pro.saas.sys.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("t_sys_menu")
@ApiModel(value = "Menu对象", description = "系统菜单表")
public class Menu implements Serializable {

    public static final String ID = "id";
    public static final String MENU_MODULE_ID = "menu_module_id";
    public static final String NAME = "name";
    public static final String PARENT_ID = "parent_id";
    public static final String TIPS = "tips";
    public static final String URL = "url";
    public static final String MENU_LEVEL = "menu_level";
    public static final String MENU_ORD = "menu_ord";
    public static final String MENU_TYPE = "menu_type";
    public static final String ICON_CLASS = "icon_class";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("模块ID")
    @TableField("menu_module_id")
    private Long menuModuleId;
    @ApiModelProperty("菜单名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上级菜单id")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("菜单提示")
    @TableField(value = "tips", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tips;
    @ApiModelProperty("菜单url")
    @TableField(value = "url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String url;
    @ApiModelProperty("层级")
    @TableField("menu_level")
    private Integer menuLevel;
    @ApiModelProperty("菜单排序")
    @TableField("menu_ord")
    private Integer menuOrd;
    @ApiModelProperty("菜单类型 0-平台菜单 1-商家端菜单 2-APP端菜单")
    @TableField(value = "menu_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String menuType;
    @ApiModelProperty("图标")
    @TableField(value = "icon_class", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String iconClass;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField("modifier")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
