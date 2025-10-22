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
 * 系统用户登录记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_user_login_record")
@ApiModel(value = "UserLoginRecord对象", description = "系统用户登录记录表")
public class UserLoginRecord implements Serializable {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String RELATE_ID = "relate_id";
    public static final String USER_LOGIN_ID = "user_login_id";
    public static final String LOGIN_IP = "login_ip";
    public static final String LOGIN_TIME = "login_time";
    public static final String LOGOUT_TIME = "logout_time";
    public static final String ONLINE = "online";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户ID")
    @TableField("user_id")
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联用户ID")
    @TableField("relate_id")
    private Long relateId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户登录ID")
    @TableField("user_login_id")
    private Long userLoginId;
    @ApiModelProperty("本次登录IP")
    @TableField(value = "login_ip", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String loginIp;
    @ApiModelProperty("本次登录时间 yyyy-MM-dd HH:mm:ss")
    @TableField("login_time")
    private Date loginTime;
    @ApiModelProperty("本次登出时间 yyyy-MM-dd HH:mm:ss")
    @TableField("logout_time")
    private Date logoutTime;
    @ApiModelProperty("在线状态 0-登出 1-在线")
    @TableField(value = "online", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String online;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
