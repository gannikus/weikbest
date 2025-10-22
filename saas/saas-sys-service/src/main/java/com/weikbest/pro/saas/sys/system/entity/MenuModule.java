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
@TableName("t_sys_menu_module")
@ApiModel(value = "MenuModule对象", description = "系统模块表")
public class MenuModule implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String MODULE_ORD = "module_ord";
    public static final String MODULE_TYPE = "module_type";
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
    @ApiModelProperty("模块编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("模块名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("模块排序")
    @TableField("module_ord")
    private Integer moduleOrd;
    @ApiModelProperty("模块类型 0-平台模块 1-商家端模块 2-APP端模块")
    @TableField(value = "module_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String moduleType;
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
