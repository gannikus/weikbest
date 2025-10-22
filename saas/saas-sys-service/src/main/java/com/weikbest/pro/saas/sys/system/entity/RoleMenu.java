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
 * 系统角色菜单关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_role_menu")
@ApiModel(value = "RoleMenu对象", description = "系统角色菜单关联表")
public class RoleMenu implements Serializable {

    public static final String ID = "id";
    public static final String ROLE_ID = "role_id";
    public static final String MENU_MODULE_ID = "menu_module_id";
    public static final String MENU_ID = "menu_id";
    public static final String PERM_ID = "perm_id";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private Long roleId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("模块ID")
    @TableField("menu_module_id")
    private Long menuModuleId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("菜单ID")
    @TableField("menu_id")
    private Long menuId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("权限项ID")
    @TableField("perm_id")
    private Long permId;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
