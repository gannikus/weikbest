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
 * 腾讯广告主授权腾讯广告第三方应用表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_tencent_adv_scope_config")
@ApiModel(value = "TencentAdvScopeConfig对象", description = "腾讯广告主授权腾讯广告第三方应用表")
public class TencentAdvScopeConfig implements Serializable {

    public static final String ID = "id";
    public static final String CLIENT_ID = "client_id";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_UIN = "account_uin";
    public static final String SCOPE_LIST = "scope_list";
    public static final String WECHAT_ACCOUNT_ID = "wechat_account_id";
    public static final String ACCOUNT_ROLE_TYPE = "account_role_type";
    public static final String ACCOUNT_TYPE = "account_type";
    public static final String ROLE_TYPE = "role_type";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String ACCESS_TOKEN_EXPIRES_IN = "access_token_expires_in";
    public static final String REFRESH_TOKEN_EXPIRES_IN = "refresh_token_expires_in";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    @TableField(value = "client_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long clientId;
    @ApiModelProperty("授权code值有效期5分钟")
    @TableField(value = "authorization_code", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String authorizationCode;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    @TableField(value = "account_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long accountId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("授权的推广帐号对应的 QQ 号")
    @TableField(value = "account_uin", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long accountUin;
    @ApiModelProperty("权限列表，若为空，则表示拥有所属应用的所有权限")
    @TableField(value = "scope_list", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String scopeList;
    @ApiModelProperty("授权的推广帐号对应的微信帐号 id")
    @TableField(value = "wechat_account_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wechatAccountId;
    @ApiModelProperty("授权账号身份类型，授权账号类型广告主,代理商,T1 账户,商务管家账户")
    @TableField(value = "account_role_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String accountRoleType;
    @ApiModelProperty("账号类型")
    @TableField(value = "account_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String accountType;
    @ApiModelProperty("角色")
    @TableField(value = "role_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String roleType;
    @ApiModelProperty("应用accessToken")
    @TableField(value = "access_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String accessToken;
    @ApiModelProperty("应用 refresh token，当 grant_type=refresh_token 时不返回")
    @TableField(value = "refresh_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refreshToken;
    @ApiModelProperty("access_token 过期时间，单位（秒）")
    @TableField("access_token_expires_in")
    private Integer accessTokenExpiresIn;
    @ApiModelProperty("refresh_token 过期时间，单位（秒），当 grant_type=refresh_token 时不返回")
    @TableField("refresh_token_expires_in")
    private Integer refreshTokenExpiresIn;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
