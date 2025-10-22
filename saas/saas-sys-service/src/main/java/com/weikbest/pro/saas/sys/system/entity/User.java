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
 * 系统用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_user")
@ApiModel(value = "User对象", description = "系统用户表")
public class User implements Serializable {

    public static final String ID = "id";
    public static final String AVATAR = "avatar";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String IS_SUPER = "is_super";
    public static final String IS_SYSUSER = "is_sysuser";
    public static final String ORG_ID = "org_id";
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
    @ApiModelProperty("头像")
    @TableField(value = "avatar", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String avatar;
    @ApiModelProperty("工号")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("用户名")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("手机号")
    @TableField(value = "phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;
    @ApiModelProperty("邮箱")
    @TableField(value = "email", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String email;
    @ApiModelProperty("是否管理员 0-否 1-是")
    @TableField(value = "is_super", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isSuper;
    @ApiModelProperty("是否系统用户 0-否 1-是")
    @TableField(value = "is_sysuser", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isSysuser;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("部门ID")
    @TableField(value = "org_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long orgId;
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
