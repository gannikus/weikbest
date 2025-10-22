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
 * 系统用户登录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_user_login")
@ApiModel(value = "UserLogin对象", description = "系统用户登录表")
public class UserLogin implements Serializable {

    public static final String ID = "id";
    public static final String LOGIN_NAME = "login_name";
    public static final String PASSWORD = "password";
    public static final String LOGIN_TYPE = "login_type";
    public static final String USER_ID = "user_id";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("登录名")
    @TableField(value = "login_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String loginName;
    @ApiModelProperty("密码")
    @TableField(value = "password", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String password;
    @ApiModelProperty("登录类型 0-工号 1-手机号")
    @TableField(value = "login_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String loginType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户ID")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
