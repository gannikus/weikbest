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
 * 系统用户控件关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_user_activex")
@ApiModel(value = "UserActivex对象", description = "系统用户控件关联表")
public class UserActivex implements Serializable {

    public static final String ID = "id";
    public static final String AVATAR = "avatar";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String ACTIVEX_DATA_STATUS = "activex_data_status";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
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
    @ApiModelProperty("控件数据状态 0-已删除 1-可用")
    @TableField(value = "activex_data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String activexDataStatus;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
