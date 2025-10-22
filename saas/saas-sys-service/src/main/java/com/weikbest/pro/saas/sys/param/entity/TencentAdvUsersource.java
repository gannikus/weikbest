package com.weikbest.pro.saas.sys.param.entity;

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
 * 腾讯广告数据上报用户行为数据源表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_tencent_adv_usersource")
@ApiModel(value = "TencentAdvUsersource对象", description = "腾讯广告数据上报用户行为数据源表")
public class TencentAdvUsersource implements Serializable {

    public static final String ID = "id";
    public static final String CLIENT_ID = "client_id";
    public static final String ACCOUNT_ID = "account_id";
    public static final String USER_ACTION_SET_ID = "user_action_set_id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String ENABLE_CONVERSION_CLAIM = "enable_conversion_claim";
    public static final String WECHAT_APP_ID = "wechat_app_id";
    public static final String DESCRIPTION = "description";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    @TableField(value = "client_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long clientId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    @TableField(value = "account_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long accountId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户行为源 id")
    @TableField(value = "user_action_set_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long userActionSetId;
    @ApiModelProperty("用户行为源名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("用户行为源类型WEB")
    @TableField(value = "type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String type;
    @ApiModelProperty("是否开启转化归因，true 表示开启，false 表示不开启，不传则默认开启 0-否 1-是")
    @TableField(value = "enable_conversion_claim", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String enableConversionClaim;
    @ApiModelProperty("微信 AppID")
    @TableField(value = "wechat_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wechatAppId;
    @ApiModelProperty("用户行为源描述")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
