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
 * 系统用户关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_user_relate")
@ApiModel(value = "UserRelate对象", description = "系统用户关联表")
public class UserRelate implements Serializable {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String RELATE_ID = "relate_id";
    public static final String RELATE_TYPE = "relate_type";
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
    @ApiModelProperty("关联其他端用户ID")
    @TableField("relate_id")
    private Long relateId;
    @ApiModelProperty("关联类型 0-系统平台用户 1-商家端用户 2-小程序端用户")
    @TableField(value = "relate_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String relateType;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
